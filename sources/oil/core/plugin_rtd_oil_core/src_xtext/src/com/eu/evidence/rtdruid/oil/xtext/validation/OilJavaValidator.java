package com.eu.evidence.rtdruid.oil.xtext.validation;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

import com.eu.evidence.rtdruid.oil.xtext.model.EType;
import com.eu.evidence.rtdruid.oil.xtext.model.EnumeratorType;
import com.eu.evidence.rtdruid.oil.xtext.model.ObjectType;
import com.eu.evidence.rtdruid.oil.xtext.model.OilFile;
import com.eu.evidence.rtdruid.oil.xtext.model.OilImplementation;
import com.eu.evidence.rtdruid.oil.xtext.model.OilObject;
import com.eu.evidence.rtdruid.oil.xtext.model.OilPackage;
import com.eu.evidence.rtdruid.oil.xtext.model.Parameter;
import com.eu.evidence.rtdruid.oil.xtext.model.ParameterRef;
import com.eu.evidence.rtdruid.oil.xtext.model.ParameterType;
import com.eu.evidence.rtdruid.oil.xtext.model.ReferenceType;
import com.eu.evidence.rtdruid.oil.xtext.model.VType;
import com.eu.evidence.rtdruid.oil.xtext.model.ValueType;
import com.eu.evidence.rtdruid.oil.xtext.model.VariantType;
import com.eu.evidence.rtdruid.oil.xtext.scoping.OilTypesFastHelper;
import com.eu.evidence.rtdruid.oil.xtext.services.IOilTypesHelper;

 

public class OilJavaValidator extends AbstractOilJavaValidator {

	private final Logger logger = Logger.getLogger(getClass());
	
	private final IOilTypesHelper helper = new OilTypesFastHelper();
	
	private static final String STR_EXPECTED_A_VALID = "Expected a valid ";
	private static final String STR_EXPECTED_A_POSITIVE_VALUE = "Expected a positive value";
	private static final String STR_PROVIDED_VALUE_DOES_NOT_FIT = "Provided value does not fit in a ";
	
	private static final BigInteger MAX_INT = BigInteger.ONE.negate();


	@Check
	public void checkModel(OilFile parameter) {
		
		logger.debug("Check Model " + parameter);
		if (parameter.getApplication() == null) {
			warning("Expected a CPU section",parameter, OilPackage.Literals.OIL_FILE__APPLICATION, IValidationCodes.MissingCpuSection);
		}
		
		for (Map<String, List<OilObject>> map : groupOilObjects(parameter).values()) {
			for (List<OilObject> list : map.values()) {
				checkParameterUniqueness(list);
			}
		}
	}

	@Check
	public void checkEnumeratorType(EnumeratorType parameter) {
		logger.debug("Check Enumerator " + parameter);
		
		EObject parent = parameter.eContainer();
		if (parent != null && parent instanceof VariantType && ((VariantType) parent).getType() == EType.BOOLEAN) {
			if (!("TRUE".equals(parameter.getName()) || "FALSE".equals(parameter.getName()))) {
				error("Only TRUE and FALSE are valid Boolean values", OilPackage.Literals.ENUMERATOR_TYPE__NAME);
			}
		}
	}

	
	@Check
	public void checkValueType(ValueType parameter) {
		logger.debug("Check ValueType " + parameter);
		
		VType type = parameter.getType();
		if (type == null) {
			error("Invalid type", OilPackage.Literals.VALUE_TYPE__TYPE);
		} else {
			
			if (parameter.isDefaultAuto()) {
				// = AUTO
				if (!parameter.isWithAuto()) {
					error("Default value AUTO requires the WITH_AUTO keyword", parameter,  OilPackage.Literals.PARAMETER_TYPE__DEFAULT_AUTO, IValidationCodes.MissingWithAuto_valueType);
				}
			} else {
				checkValueConformance(parameter.getDefaultValue(), OilPackage.Literals.PARAMETER_TYPE__DEFAULT_VALUE, type);
			}
		}
	}
	
	@Check
	public void checkParameter(Parameter parameter) {
		logger.debug("Check Parameter " + parameter);
		
		ParameterType type = parameter.getType();
		if (type == null) {
			error("Invalid parameter name", OilPackage.Literals.PARAMETER__TYPE);
		} else {
			
			if (parameter.isAuto()) {
				// = AUTO
				if (!type.isWithAuto()) {
					error("This parameter does not support AUTO value", OilPackage.Literals.PARAMETER__AUTO);
				}
				

//				if (parameter.isStructured()) {
//					error("This parameter is set to AUTO and cannot contain sub-elements", OilPackage.Literals.PARAMETER__STRUCTURED);
//				}
				
				if (parameter.getParameters().size()>0) {
					error("This parameter is set to AUTO and cannot contain sub-elements", OilPackage.Literals.PARAMETER__PARAMETERS);
				}
				
			} else {
				
				
				
				if (type instanceof ValueType) {
					checkParameterValue(parameter, (ValueType) type);
				} else if (type instanceof ReferenceType) {
					// REFERENCE Type
					checkParameterReference(parameter, (ReferenceType) type);
					
				} else if (type instanceof VariantType) {
					// VARIANT Type
					checkParameterVariant(parameter, (VariantType) type);

				} else {
					// ??
				}
			}
		}
	}
		
	private void checkParameterValue(Parameter parameter, ValueType type) {
		VType vType = type.getType();
		
		if (parameter.getValueRef() != null) {
			final String what;
			if (vType == VType.STRING) {
				what = IValidationCodes.AddQuotesToReferce_parametervalue;
			} else {
				what = IValidationCodes.FixReference_parametervalue;
			}
			error("Expected a " + vType.getName() + ", not a reference", parameter, OilPackage.Literals.PARAMETER__VALUE_REF, what);
		}
		
		if (parameter.getParameters().size()>0) {
			error("A value cannot contain sub-elements", OilPackage.Literals.PARAMETER__PARAMETERS);
		}

		// = ....
		String value = parameter.getValue();
		final EAttribute parameterValue = OilPackage.Literals.PARAMETER__VALUE;
		if (value == null)  {
			// = null
			if (parameter.getValueRef() == null) {
				error("Missing the value", parameterValue);
			}
		} else {

			checkValueConformance(value, parameterValue, vType);
		}
	}

	/**
	 * @param value
	 * @param attribute
	 * @param vType
	 */
	protected void checkValueConformance(String value, final EAttribute attribute, VType vType) {
		if (value != null) {
			switch (vType) {
			case DOUBLE:
				try {
					Double.parseDouble(value);
				} catch (NumberFormatException e) {
					error(STR_EXPECTED_A_VALID + vType.getName()+": " + value, attribute);
				}
				break;
			case FLOAT:
				try {
					Float.parseFloat(value);
				} catch (NumberFormatException e) {
					error(STR_EXPECTED_A_VALID + vType.getName()+": " + value, attribute);
				}
				break;
			case INT32:
				try {
					Integer.decode(value);
				} catch (NumberFormatException e) {
					error(STR_EXPECTED_A_VALID + vType.getName()+": " + value, attribute);
				}
				break;
			case INT64:
				try {
					Long.decode(value);
				} catch (NumberFormatException e) {
					error(STR_EXPECTED_A_VALID + vType.getName()+": " + value, attribute);
				}
				break;
			case UINT32: {
					BigInteger decimal = null;
					try {
						if (value.startsWith("0x")) {
							decimal = new BigInteger(value.substring(2), 16);
						} else {
							decimal = new BigInteger(value);
						}
					} catch (NumberFormatException e) {
						error(STR_EXPECTED_A_VALID + vType.getName()+": " + value, attribute);
					}
					if (decimal != null) {
						if (MAX_INT.compareTo(decimal)==0) {
							warning("Assign -1 to an " + vType.getName()+" means assign the maximum value", attribute);
						} else if (decimal.signum() == -1) {
							error(STR_EXPECTED_A_POSITIVE_VALUE +": " + value, attribute);
						} else {
							if (decimal.bitLength() > 32) {
								error(STR_PROVIDED_VALUE_DOES_NOT_FIT + vType.getName()+" (" + value + ")", attribute);
							}
						}
					}
				}
				break;
			case UINT64: {
					BigInteger decimal = null;
					try {
						if (value.startsWith("0x")) {
							decimal = new BigInteger(value.substring(2), 16);
						} else {
							decimal = new BigInteger(value);
						}
					} catch (NumberFormatException e) {
						error(STR_EXPECTED_A_VALID + vType.getName()+": " + value, attribute);
					}
					if (decimal != null) {
						if (MAX_INT.compareTo(decimal)==0) {
							warning("Assign -1 to an " + vType.getName()+" means assign the maximum value", attribute);
						} else if (decimal.signum() == -1) {
							error(STR_EXPECTED_A_POSITIVE_VALUE +": " + value, attribute);
						} else {
							if (decimal.bitLength() > 64) {
								error(STR_PROVIDED_VALUE_DOES_NOT_FIT + vType.getName()+" (" + value + ")", attribute);
							}
						}
					}
				}
				break;
			case STRING:
				if (!(value.startsWith("\"") && value.endsWith("\""))) {
					error(STR_EXPECTED_A_VALID + vType.getName()+": " + value, attribute);
				}
				break;
	//					default:
				// undefined type
			};
		}
	}
	
	private void checkParameterReference(Parameter parameter, ReferenceType type) {

		if (parameter.getValue() != null) {
			String value = parameter.getValue();
			if (value.startsWith("\"") && value.endsWith("\"")) {
				error(STR_EXPECTED_A_VALID + "reference, not a String", parameter, OilPackage.Literals.PARAMETER__VALUE, IValidationCodes.RemoveQuotesToReferce_parametervalue);
			} else {
				error(STR_EXPECTED_A_VALID + "reference, not a String or a number", OilPackage.Literals.PARAMETER__VALUE);
			}
		}

//		if (parameter.isStructured()) {
//			error("A reference cannot contain sub-elements", OilPackage.Literals.PARAMETER__STRUCTURED);
//		}
		
		if (parameter.getParameters().size()>0) {
			error("A reference cannot contain sub-elements", OilPackage.Literals.PARAMETER__PARAMETERS);
		}
		
		ParameterRef gRefValue = parameter.getValueRef();
		ObjectType refType = type.getType();
		if (gRefValue == null) {
			error(STR_EXPECTED_A_VALID + "reference", OilPackage.Literals.PARAMETER__VALUE_REF);
		} else {
			if (gRefValue instanceof OilObject) {
				if ( ((OilObject) gRefValue).getType() != refType) {
					error(STR_EXPECTED_A_VALID + "reference to a " + refType.getName(), OilPackage.Literals.PARAMETER__VALUE_REF);
				}
			} else {
				error(STR_EXPECTED_A_VALID + "reference to a " + refType.getName(), OilPackage.Literals.PARAMETER__VALUE_REF);
			}
		}
		
	}	
	private void checkParameterVariant(Parameter parameter, VariantType type) {
		final EType refType = type.getType();

		if (parameter.getValue() != null) {
			error(STR_EXPECTED_A_VALID + refType.getName(), OilPackage.Literals.PARAMETER__VALUE);
		}
			
		ParameterRef gRefValue = parameter.getValueRef();
		if (gRefValue == null) {
			error(STR_EXPECTED_A_VALID + refType.getName(), OilPackage.Literals.PARAMETER__VALUE_REF);
		} else {
			if (gRefValue instanceof EnumeratorType) {
				if (((EnumeratorType) gRefValue).eContainer() != type) {
					if (!(helper.getEnumeratorType(helper.computePath(parameter, false), helper.getOilImplementation(parameter)).contains(gRefValue))) {
						error(STR_EXPECTED_A_VALID + refType.getName(), OilPackage.Literals.PARAMETER__VALUE_REF);
					}
				}
			} else {
				error(STR_EXPECTED_A_VALID + refType.getName(), OilPackage.Literals.PARAMETER__VALUE_REF);
			}
		}
			
	}

	private void checkParameterUniqueness(List<OilObject> objects) {
		if (objects.size()>0) {
//			final OilTypesHelper helper = new OilTypesHelper();
			final List<OilImplementation> oilImplementation = helper.getOilImplementation(objects.get(0));
		
			List<String> path = helper.computePath(objects.get(0), false);
			Map<String, List<ParameterType>> types = asMap(helper.getParameterType(path, oilImplementation));
			
			Map<String, List<Parameter>> map = new HashMap<String, List<Parameter>>();
			for (OilObject obj : objects) {
				for (Parameter param : obj.getParameters()) {
					ParameterType ptype = param.getType();
					if (ptype != null) {
						addToMapList(map, param, ptype.getName());
					}
				}
			}
			
			checkParameterUniqueness(types, map);
				
			checkParameterUniquenessNextLevel(helper, oilImplementation, map);
		}
	}



	private void checkParameterUniquenessNextLevel(final IOilTypesHelper helper,
			final List<OilImplementation> oilImplementation, 
			Map<String, List<Parameter>> allParameters) {
		
		Map<String, List<Parameter>> _allParameters = allParameters;
		
		for (Map<String, List<Parameter>>  filteredParameters = filterVariantTypes(_allParameters);
				!filteredParameters.isEmpty(); filteredParameters = filterVariantTypes(_allParameters)) {
			_allParameters = new HashMap<String, List<Parameter>>();
			
			for (List<Parameter> list : filteredParameters.values()) {
				List<String> gpath = helper.computePath(list.get(0), true);
				Map<String, List<ParameterType>> gtypes = asMap(helper.getParameterType(gpath, oilImplementation));
				Map<String, List<Parameter>> gmap = new HashMap<String, List<Parameter>>();
					
				for (Parameter obj : list) {
					for (Parameter param : obj.getParameters()) {
						ParameterType ptype = param.getType();
						if (ptype != null) {
							addToMapList(gmap, param, ptype.getName());
						}
					}
				}
				checkParameterUniqueness(gtypes, gmap);
					
				_allParameters.putAll(gmap);
			}
		}
	}

	private Map<String, List<Parameter>> filterVariantTypes(Map<String, List<Parameter>> allParameters) {
		Map<String, List<Parameter>> answer = new HashMap<String, List<Parameter>>();
		
		int counter = 0;
		for (Entry<String, List<Parameter>> list : allParameters.entrySet()) {
			final String key = list.getKey();
			for (Parameter p: list.getValue()) {
				final ParameterRef ref = p.getValueRef();
				final ParameterType type = p.getType();
				if (type instanceof VariantType && ref != null && ref instanceof EnumeratorType) {
					final String multiValue; 
					if (type.isMultiValue()) {
						multiValue = "." + counter;
						counter++;
					} else {
						multiValue = "";
					}
					addToMapList(answer, p, key+"/"+ type.getName()
							+ multiValue
							+"/"+((EnumeratorType)ref).getName());
				}
			}
		}
		
		return answer;
	}


	private void checkParameterUniqueness(
			Map<String, List<ParameterType>> types,
			Map<String, List<Parameter>> map) {
		for (Entry<String, List<Parameter>> entry : map.entrySet()) {
			final List<Parameter> valueList = entry.getValue();
			if (valueList.size()>1 && types.containsKey(entry.getKey())) {
				final ParameterType parameterType = types.get(entry.getKey()).get(0);
				final boolean multiValue = parameterType.isMultiValue();
				if (parameterType instanceof VariantType){
					Map<String, List<Parameter>> enumTypes = new HashMap<String, List<Parameter>>();
//					for (Parameter p: valueList) {
//						final ParameterRef valueRef = p.getValueRef();
//						if (valueRef instanceof EnumeratorType) {
//							addToMapList(map, p, ((EnumeratorType) valueRef).getName());
//						}
//					}
					if (multiValue) {
//							for (List<Parameter> list: enumTypes.values()) {
//								if (list.size()>1) {
//									for (Parameter p: list) {
//										warning("Handled as distinct element (no merge done)", p, OilPackage.Literals.PARAMETER__VALUE_REF, ValidationMessageAcceptor.INSIGNIFICANT_INDEX);
//									}	
//								}
//							}
					} else {
						if (enumTypes.size()>1) {
							for (Parameter p: valueList) {
								error("Only one distinct value expected", p, OilPackage.Literals.PARAMETER__VALUE_REF, ValidationMessageAcceptor.INSIGNIFICANT_INDEX);
							}
						} else {
//								if (entry.getValue().size()>1) {
//									for (Parameter p: valueList) {
//										warning("Merged with other elements", p, OilPackage.Literals.PARAMETER__VALUE_REF, ValidationMessageAcceptor.INSIGNIFICANT_INDEX);
//									}
//								}
						}
					}
				} else {
					if (!multiValue) { 
						for (Parameter p: valueList) {
							error("Only one value expected", p, OilPackage.Literals.PARAMETER__TYPE, ValidationMessageAcceptor.INSIGNIFICANT_INDEX);
						}
					}
				}
			}
		}
	}
	
	private Map<ObjectType, Map<String, List<OilObject>>> groupOilObjects(OilFile file) {
		Map<ObjectType, Map<String, List<OilObject>>> answer = new HashMap<ObjectType, Map<String,List<OilObject>>>();
		for (OilObject obj : file.getApplication().getOilObjects()) {
			addToMapList(getMapToMapList(answer, obj.getType()), obj, obj.getName());
		}
		
		return answer;
	}



	
	private Map<String, List<ParameterType>> asMap(List<ParameterType> values) {
		Map<String, List<ParameterType>> map = new HashMap<String, List<ParameterType>>();
		for (ParameterType param: values) {
			final String objName = param.getName();

			addToMapList(map, param, objName);
		}
		
		return map;
	}



	private <T,S> void  addToMapList(Map<S, List<T>> map,
			T param, final S objName) {
		final List<T> list;
		if (map.containsKey(objName)) {
			list = map.get(objName);
		} else {
			list = new LinkedList<T>();
			map.put(objName, list);
		}
		list.add(param);
	}
	private Map<String, List<OilObject>> getMapToMapList(
			Map<ObjectType, Map<String, List<OilObject>>> answer,
			final ObjectType type) {
		final Map<String, List<OilObject>> map;
		if (answer.containsKey(type)) {
			map = answer.get(type);
		} else {
			map = new HashMap<String, List<OilObject>>();
			answer.put(type, map);
		}
		return map;
	}

}

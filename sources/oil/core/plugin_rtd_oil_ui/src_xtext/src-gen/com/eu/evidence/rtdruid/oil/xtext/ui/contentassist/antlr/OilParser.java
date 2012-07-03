/*
* generated by Xtext
*/
package com.eu.evidence.rtdruid.oil.xtext.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import com.eu.evidence.rtdruid.oil.xtext.services.OilGrammarAccess;

public class OilParser extends AbstractContentAssistParser {
	
	@Inject
	private OilGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected com.eu.evidence.rtdruid.oil.xtext.ui.contentassist.antlr.internal.InternalOilParser createParser() {
		com.eu.evidence.rtdruid.oil.xtext.ui.contentassist.antlr.internal.InternalOilParser result = new com.eu.evidence.rtdruid.oil.xtext.ui.contentassist.antlr.internal.InternalOilParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getParameterTypeAccess().getAlternatives(), "rule__ParameterType__Alternatives");
					put(grammarAccess.getValueTypeAccess().getAlternatives_3_1(), "rule__ValueType__Alternatives_3_1");
					put(grammarAccess.getValueTypeAccess().getAlternatives_3_1_0_0(), "rule__ValueType__Alternatives_3_1_0_0");
					put(grammarAccess.getValueTypeAccess().getAlternatives_3_1_0_2(), "rule__ValueType__Alternatives_3_1_0_2");
					put(grammarAccess.getValueTypeAccess().getAlternatives_3_1_1_0(), "rule__ValueType__Alternatives_3_1_1_0");
					put(grammarAccess.getValueTypeAccess().getAlternatives_3_1_1_1_1(), "rule__ValueType__Alternatives_3_1_1_1_1");
					put(grammarAccess.getValueTypeAccess().getAlternatives_6_1(), "rule__ValueType__Alternatives_6_1");
					put(grammarAccess.getValueTypeAccess().getDefaultValueAlternatives_6_1_1_0(), "rule__ValueType__DefaultValueAlternatives_6_1_1_0");
					put(grammarAccess.getVariantTypeAccess().getAlternatives_6_1(), "rule__VariantType__Alternatives_6_1");
					put(grammarAccess.getReferenceTypeAccess().getAlternatives_5_1(), "rule__ReferenceType__Alternatives_5_1");
					put(grammarAccess.getParameterAccess().getAlternatives_3(), "rule__Parameter__Alternatives_3");
					put(grammarAccess.getSpecialIdAccess().getAlternatives(), "rule__SpecialId__Alternatives");
					put(grammarAccess.getGenericValueAccess().getAlternatives(), "rule__GenericValue__Alternatives");
					put(grammarAccess.getINTAccess().getAlternatives_0(), "rule__INT__Alternatives_0");
					put(grammarAccess.getINTAccess().getAlternatives_1(), "rule__INT__Alternatives_1");
					put(grammarAccess.getINTAccess().getAlternatives_1_0_1(), "rule__INT__Alternatives_1_0_1");
					put(grammarAccess.getDOUBLEAccess().getAlternatives_0(), "rule__DOUBLE__Alternatives_0");
					put(grammarAccess.getDOUBLEAccess().getAlternatives_1(), "rule__DOUBLE__Alternatives_1");
					put(grammarAccess.getDOUBLEAccess().getAlternatives_1_1_1(), "rule__DOUBLE__Alternatives_1_1_1");
					put(grammarAccess.getDOUBLEAccess().getAlternatives_3(), "rule__DOUBLE__Alternatives_3");
					put(grammarAccess.getVTypeAccess().getAlternatives(), "rule__VType__Alternatives");
					put(grammarAccess.getETypeAccess().getAlternatives(), "rule__EType__Alternatives");
					put(grammarAccess.getObjectTypeAccess().getAlternatives(), "rule__ObjectType__Alternatives");
					put(grammarAccess.getObjectTypeRefAccess().getAlternatives(), "rule__ObjectTypeRef__Alternatives");
					put(grammarAccess.getOilFileAccess().getGroup(), "rule__OilFile__Group__0");
					put(grammarAccess.getOilFileAccess().getGroup_1(), "rule__OilFile__Group_1__0");
					put(grammarAccess.getOilImplementationAccess().getGroup(), "rule__OilImplementation__Group__0");
					put(grammarAccess.getOilObjectImplAccess().getGroup(), "rule__OilObjectImpl__Group__0");
					put(grammarAccess.getOilObjectImplAccess().getGroup_4(), "rule__OilObjectImpl__Group_4__0");
					put(grammarAccess.getOilApplicationAccess().getGroup(), "rule__OilApplication__Group__0");
					put(grammarAccess.getOilApplicationAccess().getGroup_5(), "rule__OilApplication__Group_5__0");
					put(grammarAccess.getValueTypeAccess().getGroup(), "rule__ValueType__Group__0");
					put(grammarAccess.getValueTypeAccess().getGroup_3(), "rule__ValueType__Group_3__0");
					put(grammarAccess.getValueTypeAccess().getGroup_3_1_0(), "rule__ValueType__Group_3_1_0__0");
					put(grammarAccess.getValueTypeAccess().getGroup_3_1_1(), "rule__ValueType__Group_3_1_1__0");
					put(grammarAccess.getValueTypeAccess().getGroup_3_1_1_1(), "rule__ValueType__Group_3_1_1_1__0");
					put(grammarAccess.getValueTypeAccess().getGroup_6(), "rule__ValueType__Group_6__0");
					put(grammarAccess.getValueTypeAccess().getGroup_7(), "rule__ValueType__Group_7__0");
					put(grammarAccess.getVariantTypeAccess().getGroup(), "rule__VariantType__Group__0");
					put(grammarAccess.getVariantTypeAccess().getGroup_3(), "rule__VariantType__Group_3__0");
					put(grammarAccess.getVariantTypeAccess().getGroup_3_1(), "rule__VariantType__Group_3_1__0");
					put(grammarAccess.getVariantTypeAccess().getGroup_3_1_1(), "rule__VariantType__Group_3_1_1__0");
					put(grammarAccess.getVariantTypeAccess().getGroup_6(), "rule__VariantType__Group_6__0");
					put(grammarAccess.getVariantTypeAccess().getGroup_7(), "rule__VariantType__Group_7__0");
					put(grammarAccess.getEnumeratorTypeAccess().getGroup(), "rule__EnumeratorType__Group__0");
					put(grammarAccess.getEnumeratorTypeAccess().getGroup_2(), "rule__EnumeratorType__Group_2__0");
					put(grammarAccess.getEnumeratorTypeAccess().getGroup_3(), "rule__EnumeratorType__Group_3__0");
					put(grammarAccess.getReferenceTypeAccess().getGroup(), "rule__ReferenceType__Group__0");
					put(grammarAccess.getReferenceTypeAccess().getGroup_5(), "rule__ReferenceType__Group_5__0");
					put(grammarAccess.getReferenceTypeAccess().getGroup_6(), "rule__ReferenceType__Group_6__0");
					put(grammarAccess.getOilObjectAccess().getGroup(), "rule__OilObject__Group__0");
					put(grammarAccess.getOilObjectAccess().getGroup_2(), "rule__OilObject__Group_2__0");
					put(grammarAccess.getOilObjectAccess().getGroup_3(), "rule__OilObject__Group_3__0");
					put(grammarAccess.getParameterAccess().getGroup(), "rule__Parameter__Group__0");
					put(grammarAccess.getParameterAccess().getGroup_4(), "rule__Parameter__Group_4__0");
					put(grammarAccess.getParameterAccess().getGroup_5(), "rule__Parameter__Group_5__0");
					put(grammarAccess.getINTAccess().getGroup(), "rule__INT__Group__0");
					put(grammarAccess.getINTAccess().getGroup_1_0(), "rule__INT__Group_1_0__0");
					put(grammarAccess.getDOUBLEAccess().getGroup(), "rule__DOUBLE__Group__0");
					put(grammarAccess.getDOUBLEAccess().getGroup_1_1(), "rule__DOUBLE__Group_1_1__0");
					put(grammarAccess.getOilFileAccess().getOilVersionAssignment_1_2(), "rule__OilFile__OilVersionAssignment_1_2");
					put(grammarAccess.getOilFileAccess().getImplementationAssignment_2(), "rule__OilFile__ImplementationAssignment_2");
					put(grammarAccess.getOilFileAccess().getApplicationAssignment_3(), "rule__OilFile__ApplicationAssignment_3");
					put(grammarAccess.getOilImplementationAccess().getNameAssignment_1(), "rule__OilImplementation__NameAssignment_1");
					put(grammarAccess.getOilImplementationAccess().getOilObjectsAssignment_3(), "rule__OilImplementation__OilObjectsAssignment_3");
					put(grammarAccess.getOilObjectImplAccess().getTypeAssignment_0(), "rule__OilObjectImpl__TypeAssignment_0");
					put(grammarAccess.getOilObjectImplAccess().getParametersAssignment_2(), "rule__OilObjectImpl__ParametersAssignment_2");
					put(grammarAccess.getOilObjectImplAccess().getDescriptionAssignment_4_1(), "rule__OilObjectImpl__DescriptionAssignment_4_1");
					put(grammarAccess.getOilApplicationAccess().getNameAssignment_1(), "rule__OilApplication__NameAssignment_1");
					put(grammarAccess.getOilApplicationAccess().getOilObjectsAssignment_3(), "rule__OilApplication__OilObjectsAssignment_3");
					put(grammarAccess.getOilApplicationAccess().getDescriptionAssignment_5_1(), "rule__OilApplication__DescriptionAssignment_5_1");
					put(grammarAccess.getValueTypeAccess().getTypeAssignment_1(), "rule__ValueType__TypeAssignment_1");
					put(grammarAccess.getValueTypeAccess().getWithAutoAssignment_2(), "rule__ValueType__WithAutoAssignment_2");
					put(grammarAccess.getValueTypeAccess().getNameAssignment_4(), "rule__ValueType__NameAssignment_4");
					put(grammarAccess.getValueTypeAccess().getMultiValueAssignment_5(), "rule__ValueType__MultiValueAssignment_5");
					put(grammarAccess.getValueTypeAccess().getDefaultAutoAssignment_6_1_0(), "rule__ValueType__DefaultAutoAssignment_6_1_0");
					put(grammarAccess.getValueTypeAccess().getDefaultValueAssignment_6_1_1(), "rule__ValueType__DefaultValueAssignment_6_1_1");
					put(grammarAccess.getValueTypeAccess().getDescriptionAssignment_7_1(), "rule__ValueType__DescriptionAssignment_7_1");
					put(grammarAccess.getVariantTypeAccess().getTypeAssignment_1(), "rule__VariantType__TypeAssignment_1");
					put(grammarAccess.getVariantTypeAccess().getWithAutoAssignment_2(), "rule__VariantType__WithAutoAssignment_2");
					put(grammarAccess.getVariantTypeAccess().getValuesAssignment_3_1_0(), "rule__VariantType__ValuesAssignment_3_1_0");
					put(grammarAccess.getVariantTypeAccess().getValuesAssignment_3_1_1_1(), "rule__VariantType__ValuesAssignment_3_1_1_1");
					put(grammarAccess.getVariantTypeAccess().getNameAssignment_4(), "rule__VariantType__NameAssignment_4");
					put(grammarAccess.getVariantTypeAccess().getMultiValueAssignment_5(), "rule__VariantType__MultiValueAssignment_5");
					put(grammarAccess.getVariantTypeAccess().getDefaultAutoAssignment_6_1_0(), "rule__VariantType__DefaultAutoAssignment_6_1_0");
					put(grammarAccess.getVariantTypeAccess().getDefaultValueAssignment_6_1_1(), "rule__VariantType__DefaultValueAssignment_6_1_1");
					put(grammarAccess.getVariantTypeAccess().getDescriptionAssignment_7_1(), "rule__VariantType__DescriptionAssignment_7_1");
					put(grammarAccess.getEnumeratorTypeAccess().getNameAssignment_1(), "rule__EnumeratorType__NameAssignment_1");
					put(grammarAccess.getEnumeratorTypeAccess().getParametersAssignment_2_1(), "rule__EnumeratorType__ParametersAssignment_2_1");
					put(grammarAccess.getEnumeratorTypeAccess().getDescriptionAssignment_3_1(), "rule__EnumeratorType__DescriptionAssignment_3_1");
					put(grammarAccess.getReferenceTypeAccess().getTypeAssignment_1(), "rule__ReferenceType__TypeAssignment_1");
					put(grammarAccess.getReferenceTypeAccess().getWithAutoAssignment_2(), "rule__ReferenceType__WithAutoAssignment_2");
					put(grammarAccess.getReferenceTypeAccess().getNameAssignment_3(), "rule__ReferenceType__NameAssignment_3");
					put(grammarAccess.getReferenceTypeAccess().getMultiValueAssignment_4(), "rule__ReferenceType__MultiValueAssignment_4");
					put(grammarAccess.getReferenceTypeAccess().getDefaultAutoAssignment_5_1_0(), "rule__ReferenceType__DefaultAutoAssignment_5_1_0");
					put(grammarAccess.getReferenceTypeAccess().getDefaultValueAssignment_5_1_1(), "rule__ReferenceType__DefaultValueAssignment_5_1_1");
					put(grammarAccess.getReferenceTypeAccess().getDescriptionAssignment_6_1(), "rule__ReferenceType__DescriptionAssignment_6_1");
					put(grammarAccess.getOilObjectAccess().getTypeAssignment_0(), "rule__OilObject__TypeAssignment_0");
					put(grammarAccess.getOilObjectAccess().getNameAssignment_1(), "rule__OilObject__NameAssignment_1");
					put(grammarAccess.getOilObjectAccess().getParametersAssignment_2_1(), "rule__OilObject__ParametersAssignment_2_1");
					put(grammarAccess.getOilObjectAccess().getDescriptionAssignment_3_1(), "rule__OilObject__DescriptionAssignment_3_1");
					put(grammarAccess.getParameterAccess().getTypeAssignment_1(), "rule__Parameter__TypeAssignment_1");
					put(grammarAccess.getParameterAccess().getAutoAssignment_3_0(), "rule__Parameter__AutoAssignment_3_0");
					put(grammarAccess.getParameterAccess().getValueAssignment_3_1(), "rule__Parameter__ValueAssignment_3_1");
					put(grammarAccess.getParameterAccess().getValueRefAssignment_3_2(), "rule__Parameter__ValueRefAssignment_3_2");
					put(grammarAccess.getParameterAccess().getParametersAssignment_4_1(), "rule__Parameter__ParametersAssignment_4_1");
					put(grammarAccess.getParameterAccess().getDescriptionAssignment_5_1(), "rule__Parameter__DescriptionAssignment_5_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			com.eu.evidence.rtdruid.oil.xtext.ui.contentassist.antlr.internal.InternalOilParser typedParser = (com.eu.evidence.rtdruid.oil.xtext.ui.contentassist.antlr.internal.InternalOilParser) parser;
			typedParser.entryRuleOilFile();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public OilGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(OilGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}

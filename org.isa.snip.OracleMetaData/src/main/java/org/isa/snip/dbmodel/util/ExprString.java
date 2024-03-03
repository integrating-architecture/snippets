/*Authored by www.integrating-architecture.de*/

package org.isa.snip.dbmodel.util;

import java.util.HashMap;
import java.util.Map;

/**
 * A rudimentary class to work with template strings that include variable
 * expressions.
 * 
 * <pre>
 * e.g. ExprString.newFor("Hello ${visitor} I'am ${myname}")
 * 			.put("visitor", "John")
 * 			.put("myname", "Andreas")
 * 			.build();
 * results in: "Hello John I'am Andreas"
 * </pre>
 */
public class ExprString {
	public static final String ExprPattern = "\\$\\{name\\}";

	private String target = "";
	private Map<String, String> valueMap = new HashMap<>();

	/**
	 */
	public static ExprString newFor(String pTarget) {
		return new ExprString(pTarget);
	}

	/**
	 */
	public ExprString(String pTarget) {
		target = pTarget;
	}

	/**
	 */
	private String fillExpressions(String pTemplate, Map<String, String> pValues) {
		String lResult = pTemplate;
		String lVal = "";
		String lPattern = "";

		for (String key : pValues.keySet()) {
			lPattern = ExprPattern.replace("name", key);
			lVal = pValues.getOrDefault(key, "");
			lResult = lResult.replaceAll(lPattern, lVal);
		}
		return lResult;
	}

	/**
	 */
	@Override
	public String toString() {
		return target;
	}

	/**
	 */
	public ExprString put(String pKey, String pValue) {
		valueMap.put(pKey, pValue);
		return this;
	}

	/**
	 */
	public String build() {
		String lResult = "";
		lResult = fillExpressions(target, valueMap);
		return lResult;
	}
}

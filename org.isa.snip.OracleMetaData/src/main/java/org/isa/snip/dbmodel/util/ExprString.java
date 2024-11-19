/*Authored by www.integrating-architecture.de*/

package org.isa.snip.dbmodel.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * A simple class implementing template strings that include variable expressions.
 *
 * e.g. new ExprString("Hello ${visitor} I'am ${me}")
 *          .put("visitor", "John")
 *          .put("me", "Andreas")
 *          .build();
 * results in: "Hello John I'am Andreas"
 * </pre>
 */
public class ExprString {
    private static final String PatternStart = "${";
    private static final String PatternEnd = "}";
    private static final String PatternRegex = "\\$\\{\\w+\\}";
    private static final Pattern ExprPattern = Pattern.compile(PatternRegex);

    private String template = "";
    private Map<String, String> valueMap = new HashMap<>();
    private ValueProvider provider = (String pKey) -> valueMap.getOrDefault(pKey, "");

    /**
     */
    private ExprString() {
    }

    /**
     */
    public ExprString(String pTemplate) {
        this();
        template = pTemplate;
    }

    /**
     */
    public ExprString(String pTemplate, ValueProvider pProvider) {
        this(pTemplate);
        provider = pProvider;
    }

    /**
     */
    @Override
    public String toString() {
        return template;
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
        StringBuilder lResult = new StringBuilder();
        String lPart = "";
        String lName = "";
        String lValue = "";
        Matcher lMatcher = ExprPattern.matcher(template);

        int lCurrentPos = 0;
        while (lMatcher.find()) {
            lPart = template.substring(lCurrentPos, lMatcher.start());
            lName = lMatcher.group().replace(PatternStart, "").replace(PatternEnd, "");
            lValue = provider.getValue(lName);
            lResult.append(lPart).append(lValue);
            lCurrentPos = lMatcher.end();
        }
        if (lCurrentPos < template.length()) {
            lPart = template.substring(lCurrentPos, template.length());
            lResult.append(lPart);
        }
        return lResult.toString();
    }

    /**
     */
    public static interface ValueProvider {
        String getValue(String pKey);
    }
}

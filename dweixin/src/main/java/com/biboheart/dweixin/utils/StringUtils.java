package com.biboheart.dweixin.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public class StringUtils {
    public static String replace(String source, Map<String, Object> parameter) {
        StringSubstitutor substitutor = new StringSubstitutor(parameter, "{[", "]}");
        substitutor.setEnableSubstitutionInVariables(true);
        return substitutor.replace(source);
    }
}

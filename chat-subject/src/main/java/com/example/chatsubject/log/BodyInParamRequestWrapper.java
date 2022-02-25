package com.example.chatsubject.log;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BodyInParamRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String[]> paramMap = new HashMap<>();
    private static final String BODY_AS_PARAM = "body";

    public BodyInParamRequestWrapper(HttpServletRequest request) {
        super(request);
        initParamMap(request.getParameterMap());
    }

    private void initParamMap(Map<String, String[]> paramMap) {
        this.paramMap.putAll(paramMap);
        addBodyInParamMap();
    }

    private void addBodyInParamMap() {
        try {
            BufferedReader reader = super.getReader();
            String bodyAsString = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            addBodyIntoParamMap(bodyAsString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void addBodyIntoParamMap(String bodyAsString) throws JSONException {
        if (StringUtils.isEmpty(bodyAsString)) {
            return;
        }

        JSONObject jsonObject = new JSONObject(bodyAsString);
        paramMap.put(BODY_AS_PARAM, new String[]{jsonObject.toString()});

    }

    @Override
    public String getParameter(String name) {
        String[] paramArray = getParameterValues(name);
        if (paramArray != null && paramArray.length > 0) {
            return paramArray[0];
        } else {
            return null;
        }
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] result = null;
        String[] dummyParamValue = paramMap.get(name);

        if (dummyParamValue != null) {
            result = new String[dummyParamValue.length];
            System.arraycopy(dummyParamValue, 0, result, 0, dummyParamValue.length);
        }
        return result;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Collections.unmodifiableMap(paramMap);
    }


    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(paramMap.keySet());
    }
}

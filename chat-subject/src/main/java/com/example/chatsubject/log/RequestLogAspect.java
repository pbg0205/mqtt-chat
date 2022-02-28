package com.example.chatsubject.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Aspect
@Order(1)
@Component
public class RequestLogAspect {

    @Around("Pointcuts.requestLogPointcut()")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request
                = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();

        try {
            Map<String, Object> requestHeaderMap = getRequestHeaderMap(request);
            JSONObject requestJsonBody = getRequestBody(request);
            log.info("{Request : header : {}, body : {}}", requestHeaderMap, requestJsonBody);
        } catch (JSONException e) {
            log.error("LogRequest Error");
        }

        return joinPoint.proceed();
    }

    private Map<String, Object> getRequestHeaderMap(HttpServletRequest request) {
        Map<String, Object> headerParamMap = new LinkedHashMap<>();

        headerParamMap.put("RemoteAddress", request.getRemoteAddr());
        headerParamMap.put("RequestURI", request.getRequestURI());
        headerParamMap.put("HttpMethod", request.getMethod());
        headerParamMap.put("QueryString", request.getQueryString());
        headerParamMap.put("ContentType", request.getContentType());
        headerParamMap.put("Locale", request.getLocale());

        return headerParamMap;
    }

    private JSONObject getRequestBody(HttpServletRequest request) throws JSONException {
        JSONObject jsonObject;
        try {
            jsonObject = transformToJson(request);
        } catch (JSONException e) {
            log.error("body transform error");
            throw new JSONException("body transform error");
        }

        return jsonObject;
    }

    private static JSONObject transformToJson(HttpServletRequest request) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String param = params.nextElement();
            jsonObject.put(param, request.getParameter(param));
        }

        return jsonObject;
    }

}

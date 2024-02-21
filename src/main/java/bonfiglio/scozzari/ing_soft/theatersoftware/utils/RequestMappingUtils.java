package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.ExceptionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

public class RequestMappingUtils {

    public static String extractPath(HandlerMethod handlerMethod) {
        RequestMapping classMapping = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), RequestMapping.class);
        RequestMapping methodMapping = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), RequestMapping.class);

        String classPath = (classMapping != null && classMapping.value().length > 0) ? classMapping.value()[0] : "";
        String methodPath = (methodMapping != null && methodMapping.value().length > 0) ? methodMapping.value()[0] : "";

        return classPath + methodPath;
    }

    public static String extractClassName(HandlerMethod handlerMethod) {
        return handlerMethod.getBeanType().getSimpleName();
    }

    public static String extractMethodName(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().getName();
    }

}

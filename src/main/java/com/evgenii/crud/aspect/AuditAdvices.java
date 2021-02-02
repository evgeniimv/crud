package com.evgenii.crud.aspect;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Stream;

@Aspect
@Component
public class AuditAdvices {
    private static final String SERVICE = "CLAIM_BACKEND";
    private static final String SESSION_ID = "DEFAULT_SESSION_ID";
    private static final String USER_ID = "DEFAULT_USER_ID";
    private static final String DELIMITER = ":";

    @AfterReturning(value = "@annotation(Auditable)", argNames = "userId")
    public void beforeAdvice(JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(Auditable.class)) {
            final Auditable auditable = method.getAnnotation(Auditable.class);
            final EventCode eventCode = auditable.value();

            System.out.println("Code: " + eventCode.name()
                    + ". Title: " + eventCode.getTitle()
                    + ". Service: " + SERVICE
                    + ". Message: " + buildMessage(eventCode.getMessageTemplate(), getMethodAttr(joinPoint, auditable.params())));
        }
    }

    private Object[] getMethodAttr(JoinPoint joinPoint, String[] params) {
        final String[] init = new String[]{Instant.now().toString(), SESSION_ID, USER_ID};
        final CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        final String[] parameterNames = codeSignature.getParameterNames();
        final Object[] parameterValues = joinPoint.getArgs();

        return ArrayUtils.addAll(init, Stream.of(params)
                .map(name -> {
                    Object atr;
                    if (name.contains(DELIMITER)) {
                        final String[] atrChain = name.split(DELIMITER);
                        Object parentAtr = parameterValues[ArrayUtils.indexOf(parameterNames, atrChain[0])];
                        try {
                            atr = getNestedAttribute(parentAtr, atrChain);
                        } catch (InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                            atr = StringUtils.EMPTY;
                        }
                    } else {
                        atr = parameterValues[ArrayUtils.indexOf(parameterNames, name)];
                    }
                    return atr.toString();
                })

                .toArray());
    }

    private Object getNestedAttribute(Object parentAtr, String[] atrChain) throws InvocationTargetException, IllegalAccessException {
        Object keyObject = parentAtr;
        for (int i = 1; i < atrChain.length; i++) {
            final String key = atrChain[i];
            final Optional<Method> methodToInvoke = Stream.of(keyObject.getClass().getDeclaredMethods())
                    .filter(method -> method.getName().equals("get" + StringUtils.capitalize(key)))
                    .findFirst();
            if (methodToInvoke.isPresent()) {
                keyObject = methodToInvoke.get().invoke(keyObject);
            }
        }
        return keyObject;
    }

    private String buildMessage(String template, Object... messageAttr) {
        return String.format(template, messageAttr);
    }
}

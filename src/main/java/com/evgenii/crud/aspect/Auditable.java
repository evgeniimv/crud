package com.evgenii.crud.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditable {
    EventCode value();

    /**
     * Can be specified in next ways:
     * <pre>
     * 1) {@code params = {"id"}}
     * 2) {@code params = {"id", "name"}}
     * </pre>
     * In order to audit properties of some complex Object (ex. UserDto):
     * <pre>
     * 3) {@code params = {"userDto:name", "userDto:email"}}
     * 4) {@code params = {"userDto:child:name"}}
     * </pre>
     * In this case corresponding UserDto class should contain getName(), getEmail() and getChild()
     * methods respectively
     *
     * <p>NOTE: {@link com.evgenii.crud.aspect.AuditAdvices#getNestedAttribute(java.lang.Object, java.lang.String[])}</p>

     * @return specified array otherwise empty array
     */
    String[] params() default {};
}

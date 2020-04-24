package com.xyz.modules.system.util.annotation;

import com.xyz.modules.system.util.DictEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 字典标识, 将次注解添加在实体的类的字典字段上.
 * @author dadovicn
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Dict {
    /**
     * 对应的字典name
     */
    DictEnum value() default DictEnum.EMPTY;

    /**
     * Whether it is multiple choice
     */
    boolean multiple() default false;

    /**
     * Whether it is multiple grade
     */
    boolean level() default false;
}

package org.toehisa.orm.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //разрешено вешать только на классы
@Retention(RetentionPolicy.RUNTIME) //Говорим jvm чтобы сохранил в памяти для доступа в рантайме
public @interface Table {
    String name();
}

package com.kaidash.mobileapplicationsservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ContentRateConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentRate {

    public String value();

    public String message();

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}

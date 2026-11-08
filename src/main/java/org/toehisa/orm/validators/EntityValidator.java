package org.toehisa.orm.validators;

import org.toehisa.orm.annotation.Entity;

public class EntityValidator {
    public void validate(Class<?> entityReflection) {
        isEntityContinueElseException(entityReflection);
    }

    private void isEntityContinueElseException(Class<?> reflection) {
        if (!isEntity(reflection)) {
            throw new IllegalArgumentException("Объект не является сущностью!");
        }
    }

    private static boolean isEntity(Class<?> reflection) {
        return reflection.isAnnotationPresent(Entity.class);
    }
}


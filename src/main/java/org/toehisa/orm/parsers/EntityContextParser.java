package org.toehisa.orm.parsers;

import org.toehisa.orm.annotation.Column;
import org.toehisa.orm.annotation.Id;
import org.toehisa.orm.annotation.Table;
import org.toehisa.orm.strategies.QueryContext;

import java.lang.reflect.Field;
import java.util.HashMap;

public class EntityContextParser {
    public QueryContext parse(Object entity) {
        Class<?> reflection = entity.getClass();
        QueryContext context = new QueryContext();

        context.tableName = getTableName(reflection);
        context.columnsData = new HashMap<>();

        Field[] fields = reflection.getDeclaredFields();

        for (Field field : fields) {
            if (isColumn(field)) {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name();
                Object columnData = getColumnData(field, entity);

                context.columnsData.put(columnName, columnData);

                if (isId(field)) {
                    context.idValue = columnData;
                    context.idName = columnName;
                }
            }
        }

        return context;
    }

    private Object getColumnData(Field field, Object entity) {
        field.setAccessible(true);
        try {
            return field.get(entity);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTableName(Class<?> reflection) {
        if (isTable(reflection)) {
            return reflection.getAnnotation(Table.class).name();
        } else {
            return getClassName(reflection);
        }
    }

    private String getClassName(Class<?> reflection) {
        return reflection.getSimpleName().toLowerCase();
    }

    private static boolean isTable(Class<?> reflection) {
        return reflection.isAnnotationPresent(Table.class);
    }

    private static boolean isColumn(Field field) {
        return field.isAnnotationPresent(Column.class);
    }

    private static boolean isId(Field field) {
        return field.isAnnotationPresent(Id.class);
    }

}

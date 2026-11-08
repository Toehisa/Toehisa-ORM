package org.toehisa.orm.strategies;

import java.util.Map;

public class InsertStrategy implements CRUDStrategy {
    @Override
    public String generateQuery(QueryContext context) {
        StringBuilder insert = new StringBuilder(String.format("INSERT INTO %s (", context.tableName));
        StringBuilder values = new StringBuilder("VALUES (");

        for (Map.Entry<String, Object> entry : context.columnsData.entrySet()) {
            if (isId(entry, context)) {continue;}

            String insertStr = String.format("%s, ", entry.getKey());
            String valuesStr = String.format("'%s', ", entry.getValue());

            insert.append(insertStr);
            values.append(valuesStr);
        }

        insert.delete(insert.length() - 2, insert.length());
        values.delete(values.length() - 2, values.length());

        insert.append(')');
        values.append(')').append(';');

        return insert + " " + values;
    }

    private boolean isId(Map.Entry<String, Object> entry, QueryContext context) {
        return entry.getKey().equals(context.idName);
    }
}

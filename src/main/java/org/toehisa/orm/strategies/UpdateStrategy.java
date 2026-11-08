package org.toehisa.orm.strategies;

import java.util.Map;
import java.util.Set;

public class UpdateStrategy implements CRUDStrategy {
    @Override
    public String generateQuery(QueryContext context) {
        String update = String.format("UPDATE %s", context.tableName);
        StringBuilder set = new StringBuilder("SET ");
        String where = String.format("WHERE %s=%s", context.idName, context.idValue);

        for (var entry : entrySet(context)) {
            if (isId(entry, context)) {continue;}

            String setStr = String.format("%s='%s', ", entry.getKey(), entry.getValue());
            set.append(setStr);
        }

        set.delete(set.length() - 2, set.length());

        return update + " " + set + " " + where;
    }

    private Set<Map.Entry<String, Object>> entrySet(QueryContext context) {
        return context.columnsData.entrySet();
    }

    private boolean isId(Map.Entry<String, Object> entry, QueryContext context) {
        return entry.getKey().equals(context.idName);
    }
}

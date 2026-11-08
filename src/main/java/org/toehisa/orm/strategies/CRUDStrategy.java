package org.toehisa.orm.strategies;

public interface CRUDStrategy {
    String generateQuery(QueryContext context);
}

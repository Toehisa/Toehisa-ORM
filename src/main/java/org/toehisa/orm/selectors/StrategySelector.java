package org.toehisa.orm.selectors;

import org.toehisa.orm.strategies.CRUDStrategy;
import org.toehisa.orm.strategies.InsertStrategy;
import org.toehisa.orm.strategies.QueryContext;
import org.toehisa.orm.strategies.UpdateStrategy;

import java.util.Objects;

public class StrategySelector {
    public CRUDStrategy select(QueryContext context) {
        if (isEmptyId(context.idValue)) {
            return new InsertStrategy();
        } else {
            return new UpdateStrategy();
        }
    }


    private boolean isEmptyId(Object idValue) {
        return Objects.isNull(idValue);
    }
}

package org.toehisa.orm.providers;

import org.toehisa.orm.parsers.EntityContextParser;
import org.toehisa.orm.selectors.StrategySelector;
import org.toehisa.orm.strategies.CRUDStrategy;
import org.toehisa.orm.strategies.QueryContext;
import org.toehisa.orm.validators.EntityValidator;


public class EntityManager {
    private final EntityValidator validator = new EntityValidator();
    private final EntityContextParser contextParser = new EntityContextParser();
    private final StrategySelector strategySelector = new StrategySelector();

    public String save(Object entity) {
        validator.validate(entity.getClass());//Если есть аннотация Entity, то далее, иначе exception

        QueryContext context = contextParser.parse(entity);//Собираем все метаданные и билдим контекст
        CRUDStrategy strategy = strategySelector.select(context);//Выбор стратегии по наличии id

        return strategy.generateQuery(context);//Генерим запрос
    }
}

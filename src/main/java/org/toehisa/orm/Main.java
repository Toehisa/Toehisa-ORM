package org.toehisa.orm;

import org.toehisa.dao.tables.User;
import org.toehisa.orm.providers.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = new EntityManager();

        // Сценарий 1: Новый пользователь (ID = null)
        User newUser = new User(null, "toehisa", 25);
        System.out.println(em.save(newUser));
        // Ожидаем: INSERT INTO users ...

        // Сценарий 2: Существующий пользователь (ID = 1)
        User existingUser = new User(1L, "toehisa", 26);
        System.out.println(em.save(existingUser));
        // Ожидаем: UPDATE users SET ... WHERE id=1;
    }
}

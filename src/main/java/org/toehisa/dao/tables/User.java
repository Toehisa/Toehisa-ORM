package org.toehisa.dao.tables;


import org.toehisa.orm.annotation.Column;
import org.toehisa.orm.annotation.Entity;
import org.toehisa.orm.annotation.Id;
import org.toehisa.orm.annotation.Table;

@Entity//Флаг, взять в управление ORM
@Table(name="users")//говорим что это таблица users
public class User {
    @Id//маркировка первичного ключа
    @Column(name="id")
    private Long id;
    @Column(name="имя")
    private String name;
    @Column(name="возраст")
    private Integer age;

    //Пустой конструктор для доступа через рефлексию, чтобы создать прокси объект, ну и для создания пустого юзера
    //к сожалению полностью имутабельным мы сделать не можем, но хотябы протектед тк разрешает для наследников.
    protected User(){}

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name= name;
        this.age = age;
    }

    public Long id() {
        return id;
    }

    public Integer age() {
        return age;
    }

    public String name() {
        return name;
    }
}

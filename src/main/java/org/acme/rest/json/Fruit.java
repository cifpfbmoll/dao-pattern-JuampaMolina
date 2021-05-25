package org.acme.rest.json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;

@Entity
@Table(name = "fruits")
public class Fruit extends PanacheEntity {

    @NotNull
    @Column(unique = true)
    public String name;

    @Column
    public String description;

    public Fruit() {

    }

    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
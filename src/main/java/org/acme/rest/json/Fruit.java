package org.acme.rest.json;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.smallrye.common.constraint.NotNull;

@Entity
@Table(name = "fruits")
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(unique = true, name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    public Fruit() {

    }

    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
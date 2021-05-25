package org.acme.rest.json;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActiveRecord {

    public List<Fruit> getFruits() {
        return Fruit.findAll().list();
    }

    public void addFruit(Fruit fruit) {
        fruit.persist();
    }

    public void deleteFruit(Fruit fruit) {
        fruit.delete();
    }
}

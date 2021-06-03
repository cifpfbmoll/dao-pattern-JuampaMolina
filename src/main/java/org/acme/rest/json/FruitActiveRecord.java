package org.acme.rest.json;

import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

@Singleton
public class FruitActiveRecord {

    public FruitActiveRecord() {

    }

    public List<Fruit> getFruits() {
        return Fruit.findAll().list();
    }

    public Optional<Fruit> getFruit(String name) {
        return Fruit.find("name", name).firstResultOptional();
    }

    public void addFruit(Fruit fruit) {
        fruit.persist();
    }

    public Fruit deleteFruit(Fruit fruit) {
        fruit.delete();
        return fruit;
    }
}

package org.acme.rest;

import java.util.Optional;

import javax.inject.Inject;

import org.acme.rest.json.Fruit;
import org.acme.rest.json.FruitActiveRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FruitActiveRecordTest {

    @Inject
    FruitActiveRecord ar; // se inicializa como null >:(

    @Test
    public void checkSetupTest() {
        Assertions.assertThat(ar.getFruits()).hasSize(2);
    }

    @Test
    public void containsFruitTest() {
        Assertions.assertThat(ar.getFruits().stream().anyMatch(f -> f.name.equalsIgnoreCase("Orange"))).isTrue();
    }

    @Test
    public void removeFruitTest() {
        Optional<Fruit> fruit = ar.getFruit("Orange");
        if (fruit.isPresent()) {
            ar.deleteFruit(fruit.get());
        }

        Assertions.assertThat(ar.getFruits()).hasSize(1);
        Assertions.assertThat(ar.getFruits().stream().anyMatch(f -> f.name.equalsIgnoreCase("Orange"))).isFalse();

        Fruit.persist(new Fruit("Orange", "Summer fruit"));
    }

    @Test
    public void addFruitTest() {
        ar.addFruit(new Fruit("Mandarina", "No valgo pa zumos"));
        Assertions.assertThat(ar.getFruits()).hasSize(3);
        Assertions.assertThat(ar.getFruits().stream().anyMatch(f -> f.name.equalsIgnoreCase("kiwi"))).isTrue();

        Fruit fruit = Fruit.find("name", "Mandarina").firstResult();
        fruit.delete();
    }

    @Test
    public void getFruitTest() {
        Assertions.assertThat(ar.getFruit("Orange").get()).hasFieldOrPropertyWithValue("name", "Orange");
        Assertions.assertThat(ar.getFruit("Water")).isEmpty();
    }
}

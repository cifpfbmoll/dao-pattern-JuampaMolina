package org.acme.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;

@QuarkusTest
@Transactional
public class FruitActiveRecordTest {

    @Inject
    FruitActiveRecord activeRecord; // se inicializa como null >:(

    @Test
    void test_lookupService() {
        Assertions.assertThat(this.activeRecord).isNotNull();
    }

    @Test
    public void checkSetupTest() {
        Assertions.assertThat(this.activeRecord.getFruits()).hasSize(2);
    }

    @Test
    public void containsFruitTest() {
        Assertions.assertThat(this.activeRecord.getFruits().stream().anyMatch(f -> f.name.equalsIgnoreCase("Orange")))
                .isTrue();
    }

    @Test
    public void removeFruitTest() {
        Optional<Fruit> fruit = this.activeRecord.getFruit("Orange");
        if (fruit.isPresent()) {
            this.activeRecord.deleteFruit(fruit.get());
        }

        Assertions.assertThat(this.activeRecord.getFruits()).hasSize(1);
        Assertions.assertThat(this.activeRecord.getFruits().stream().anyMatch(f -> f.name.equalsIgnoreCase("Orange")))
                .isFalse();

        Fruit.persist(new Fruit("Orange", "Summer fruit"));
    }

    @Test
    public void addFruitTest() {
        this.activeRecord.addFruit(new Fruit("Mandarina", "No valgo pa zumos"));
        Assertions.assertThat(this.activeRecord.getFruits()).hasSize(3);
        Assertions
                .assertThat(this.activeRecord.getFruits().stream().anyMatch(f -> f.name.equalsIgnoreCase("Mandarina")))
                .isTrue();

        Fruit fruit = Fruit.find("name", "Mandarina").firstResult();
        fruit.delete();
    }

    @Test
    public void getFruitTest() {
        Assertions.assertThat(this.activeRecord.getFruit("Orange").get()).hasFieldOrPropertyWithValue("name", "Orange");
        Assertions.assertThat(this.activeRecord.getFruit("Water")).isEmpty();
    }
}

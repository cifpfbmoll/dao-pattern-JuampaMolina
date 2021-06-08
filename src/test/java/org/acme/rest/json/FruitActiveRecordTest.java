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
    FruitService fruitService;

    @Test
    void test_lookupService() {
        Assertions.assertThat(this.fruitService).isNotNull();
    }

    @Test
    public void checkSetupTest() {
        Assertions.assertThat(this.fruitService.getFruits()).hasSize(2);
    }

    @Test
    public void containsFruitTest() {
        Assertions.assertThat(this.fruitService.getFruits().stream().anyMatch(f -> f.name.equalsIgnoreCase("Orange")))
                .isTrue();
    }

    @Test
    public void removeFruitTest() {
        Optional<Fruit> fruit = this.fruitService.getFruit("Orange");
        if (fruit.isPresent()) {
            this.fruitService.deleteFruit(fruit.get());
        }

        Assertions.assertThat(this.fruitService.getFruits()).hasSize(1);
        Assertions.assertThat(this.fruitService.getFruits().stream().anyMatch(f -> f.name.equalsIgnoreCase("Orange")))
                .isFalse();

        this.fruitService.addFruit(new Fruit("Orange", "Summer fruit"));
    }

    @Test
    public void addFruitTest() {
        this.fruitService.addFruit(new Fruit("Mandarina", "No valgo pa zumos"));
        Assertions.assertThat(this.fruitService.getFruits()).hasSize(3);
        Assertions
                .assertThat(this.fruitService.getFruits().stream().anyMatch(f -> f.name.equalsIgnoreCase("Mandarina")))
                .isTrue();

        Optional<Fruit> fruit = fruitService.getFruit("Mandarina");
        if (fruit.isPresent()) {
            this.fruitService.deleteFruit(fruit.get());
        }
    }

    @Test
    public void getFruitTest() {
        Assertions.assertThat(this.fruitService.getFruit("Orange").get()).hasFieldOrPropertyWithValue("name", "Orange");
        Assertions.assertThat(this.fruitService.getFruit("Water")).isEmpty();
    }
}

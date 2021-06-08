package org.acme.rest.json;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FruitService {

    @Inject
    RepositoryFruit repoFruit;

    public FruitService() {

    }

    public List<Fruit> getFruits() {
        return repoFruit.listAll();
    }

    public Optional<Fruit> getFruit(String name) {
        return repoFruit.findByName(name);
    }

    public void addFruit(Fruit fruit) {
        this.repoFruit.persist(fruit);
    }

    public Fruit deleteFruit(Fruit fruit) {
        this.repoFruit.delete(fruit);
        return fruit;
    }
}

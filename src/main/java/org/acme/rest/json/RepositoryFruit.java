package org.acme.rest.json;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;

@ApplicationScoped
public class RepositoryFruit implements PanacheRepositoryBase<Fruit, Long> {

    public List<Fruit> listAll() {
        return this.listAll(Sort.by("name"));
    }

    public Optional<Fruit> findByName(String name) {
        return this.find("name", name).firstResultOptional();
    }

    public void deleteFruit(Fruit fruit) {
        this.delete(fruit);
    }
}
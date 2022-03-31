package com.cydeo.pizzacloud.repository;

import com.cydeo.pizzacloud.model.Pizza;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Data
public class PizzaRepository {

    private static List<Pizza> pizzaList = new ArrayList<>();

    public Pizza createPizza(Pizza pizzaToSave) {
        pizzaList.add(pizzaToSave);
        return pizzaToSave;
    }

    public List<Pizza> readAll() {
        return pizzaList;
    }

    public Pizza getPizzaById(UUID id) {
        return  pizzaList.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(new Pizza());
    }

}

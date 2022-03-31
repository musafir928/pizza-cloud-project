package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.bootstrap.DataGenerator;
import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@Data
@AllArgsConstructor
public class DesignPizzaController {

    private PizzaRepository pizzaRepository;

    @GetMapping("/design")
    public String showDesignForm(Model model) {

        model.addAttribute("pizza", new Pizza());

        model.addAttribute("cheeses", DataGenerator.cheeseTypeList);
        model.addAttribute("sauces", DataGenerator.sauceTypeList);
        model.addAttribute("toppings", DataGenerator.toppingTypeList);

        return "/design";

    }

    @PostMapping("/createPizza")
    public String processPizza(Pizza pizza) {

        pizza.setId(UUID.randomUUID());
        pizzaRepository.createPizza(pizza);

        return "redirect:/orders/current?pizzaId=" + pizza.getId();

    }

}

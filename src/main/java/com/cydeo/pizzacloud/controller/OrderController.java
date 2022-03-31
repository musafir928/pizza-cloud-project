package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.model.PizzaOrder;
import com.cydeo.pizzacloud.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final PizzaRepository pizzaRepository;

    public OrderController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/current")
    public String orderForm(@RequestParam UUID pizzaId, Model model) {

        PizzaOrder pizzaOrder = new PizzaOrder();

        // Fix the getPizza method below in line 49.
        pizzaOrder.setPizza(getPizza(pizzaId));

        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }

    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable UUID pizzaId, PizzaOrder pizzaOrder) {
        System.out.println(pizzaId);

        // Save the order

        pizzaOrder.setPizza(getPizza(pizzaId));
        return "redirect:/home";
    }

    private Pizza getPizza(UUID pizzaId) {
        // Get the pizza from repository based on it's id
        System.out.println("pizzaRepository.getPizzaById(pizzaId) = " + pizzaRepository.getPizzaById(pizzaId).getId());
        return pizzaRepository.getPizzaById(pizzaId);
    }

}

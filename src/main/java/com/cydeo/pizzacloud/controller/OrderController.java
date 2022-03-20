package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.exception.PizzaNotFoundException;
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
        pizzaOrder.setPizza(getPizza(pizzaId));

        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }

    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable("pizzaId") UUID pizzaId, PizzaOrder pizzaOrder) {

        // Save the order

        pizzaOrder.setPizza(getPizza(pizzaId));
        System.out.println(pizzaOrder);
        System.out.println("Order is processed");
        return "redirect:/home";
    }

    private Pizza getPizza(UUID pizzaId) {
        return pizzaRepository.readAll().stream().filter(p -> p.getId().equals(pizzaId)).findFirst().orElseThrow(() -> new PizzaNotFoundException("Pizza not found!"));
    }

}

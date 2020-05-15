package mk.finki.ukim.mk.lab.web.controllers;

import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PizzasController {

    private final PizzaService pizzaService;

    public PizzasController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping(path = "/pizzas/create")
    public String createPizza(@RequestParam(value = "pizza") String pizzaName, @RequestParam String description){
        this.pizzaService.create(pizzaName, description);
        return "redirect:/";
    }

}

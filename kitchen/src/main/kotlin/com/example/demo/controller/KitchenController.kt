package com.example.demo.controller

import com.example.demo.model.Food
import com.example.demo.service.KitchenService
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class KitchenController(val kitchenService: KitchenService) {

    @QueryMapping
    fun getAllFoodsKitchen(): List<Food> {
        return kitchenService.getAllFoodsKitchen();
    }
}
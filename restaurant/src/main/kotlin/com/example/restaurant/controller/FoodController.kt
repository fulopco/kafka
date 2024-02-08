package com.example.restaurant.controller

import com.example.restaurant.model.Food
import com.example.restaurant.service.FoodService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class FoodController(val foodService: FoodService) {

    @QueryMapping
    fun getAllFoods(): List<Food> {
        return foodService.getAllFoods();
    }

    @QueryMapping
    fun getFood(@Argument id: Int): Food {
        return foodService.getFood(id);
    }

    @MutationMapping
    fun createFood(@Argument name: String,@Argument  price: Int): Food {
        return foodService.createFood(name, price);
    }

    @MutationMapping
    fun updateFood(@Argument uuid: String,@Argument name: String,@Argument price: Int): Food {
        return foodService.updateFood(uuid, name, price);
    }

    @MutationMapping
    fun deleteFood(@Argument uuid: String): Food {
        return foodService.deleteFood(uuid);
    }

    @QueryMapping
    fun order(@Argument id: Int): String {
        return foodService.orderFood(id);
    }
}
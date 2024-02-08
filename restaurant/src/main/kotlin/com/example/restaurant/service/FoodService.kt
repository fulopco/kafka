package com.example.restaurant.service

import com.example.restaurant.model.Food
import com.example.restaurant.model.Order
import com.example.restaurant.repository.FoodRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FoodService(
    val foodRepository: FoodRepository,
    val kafkaTemplate: KafkaTemplate<String, String>) {

    fun getAllFoods(): List<Food> {
        return foodRepository.findAll();
    }

    fun getFood(id: Int): Food {
        return foodRepository.findById(id).orElse(null);
    }

    fun getFoodByUUID(uuid: String): Food {
        return foodRepository.findByUuid(uuid).orElse(null);
    }

    fun orderFood(id: Int): String {
        val objectMapper: ObjectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(Order("Corinna", getFood(id)))
        kafkaTemplate.sendDefault(json)
        return "Send!"
    }

    fun createFood(name: String, price: Int): Food {
        val food = Food(name=name, price=price)
        sendToKitchen("create", food);
        return food;
    }

    fun updateFood(uuid: String, name: String, price: Int): Food {
        var food = getFoodByUUID(uuid);
        food.name = name;
        food.price = price;
        sendToKitchen("update", food)
        return food;
    }

    fun deleteFood(uuid: String): Food {
        var food = getFoodByUUID(uuid);
        sendToKitchen("delete", food)
        return food;
    }

    fun sendToKitchen(command: String, food: Food) {
        val json = ObjectMapper().writeValueAsString(food);
        kafkaTemplate.sendDefault(command, json)
    }

    @KafkaListener(topics = ["kitchenTopic"], groupId = "asd")
    private fun listenOnMyTopic(message: ConsumerRecord<String, String>) {
        if(message.key().equals("create")) {
            val food: Food = ObjectMapper().readValue(message.value(), Food::class.java)
            foodRepository.save(food);
        } else if (message.key().equals("update")) {
            val food: Food = ObjectMapper().readValue(message.value(), Food::class.java)
            foodRepository.save(food);
        } else if (message.key().equals("delete")) {
            val food: Food = ObjectMapper().readValue(message.value(), Food::class.java)
            foodRepository.delete(food);
        }
    }



}
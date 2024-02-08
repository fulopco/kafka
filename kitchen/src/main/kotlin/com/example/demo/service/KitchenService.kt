package com.example.demo.service

import com.example.demo.model.Food
import com.example.demo.model.Order
import com.example.demo.repository.KitchenRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class KitchenService(
    val kitchenRepository: KitchenRepository,
    val kafkaTemplate: KafkaTemplate<String, String>
) {

    @KafkaListener(topics = ["myTopic"], groupId = "asd")
    fun listen(message: ConsumerRecord<String, String>) {
        if (message.key().equals("create")) {
            val food: Food = ObjectMapper().readValue(message.value(), Food::class.java);
            saveFood(food);
        } else if (message.key().equals("update")) {
            val food: Food = ObjectMapper().readValue(message.value(), Food::class.java);
            updateFood(food);
        } else if (message.key().equals("delete")) {
            val food: Food = ObjectMapper().readValue(message.value(), Food::class.java);
            deleteFood(food);
        }
    }

    fun saveFood(food: Food): Food {
        val uuid: UUID = UUID.randomUUID();
        food.uuid = uuid.toString();
        sendToKitchenTopic(food, "create");
        return kitchenRepository.save(food);
    }

    fun updateFood(food: Food) {
        var foodToChange: Food = kitchenRepository.findByUuid(food.uuid).orElse(null)
        foodToChange.name = food.name;
        foodToChange.price = food.price;
        sendToKitchenTopic(foodToChange, "update")
        kitchenRepository.save(foodToChange);
    }

    fun deleteFood(food: Food) {
        var foodToDelete: Food = kitchenRepository.findByUuid(food.uuid).orElse(null);
        sendToKitchenTopic(foodToDelete, "delete")
        kitchenRepository.delete(foodToDelete)
    }

    fun sendToKitchenTopic(food: Food, command: String) {
        val objectMapper: ObjectMapper = ObjectMapper();
        val json: String = objectMapper.writeValueAsString(food);
        kafkaTemplate.send("kitchenTopic", command, json);
    }

    fun getAllFoodsKitchen(): List<Food> {
        return kitchenRepository.findAll();
    }

}
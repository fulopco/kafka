package com.example.restaurant.repository

import com.example.restaurant.model.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FoodRepository: JpaRepository<Food, Int> {
    fun findByUuid(uuid: String): Optional<Food>
    fun deleteFoodByUuid(uuid: String): Food;
}
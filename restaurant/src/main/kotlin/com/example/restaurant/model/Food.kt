package com.example.restaurant.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.UUID

@Entity
@NoArgsConstructor
@AllArgsConstructor
class Food(var uuid: String = "", var name: String, var price: Int) {

    @Id
    @GeneratedValue
    var id : Int = 0;
}
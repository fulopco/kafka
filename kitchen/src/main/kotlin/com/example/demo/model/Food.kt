package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@NoArgsConstructor
@AllArgsConstructor
data class Food(
    @Id
    @GeneratedValue
    @JsonProperty("id") var id: Int,
    @JsonProperty("uuid")var uuid: String,
    @JsonProperty("name") var name: String,
    @JsonProperty("price") var price: Int
)
{

}
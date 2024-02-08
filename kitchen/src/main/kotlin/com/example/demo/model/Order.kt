package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Order @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("food") var food: Food
) {
}
package com.example.mytrainingsession


import java.io.Serializable

data class Exercise(
    val name: String,
    val description: String,
    val durationInSeconds: Int,
    val gifImage: Int
) : Serializable


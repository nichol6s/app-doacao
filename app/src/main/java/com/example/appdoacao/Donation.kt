package com.example.appdoacao

import java.io.Serializable

data class Donation(
    val id: String,
    val name: String,
    val amount: Double
) : Serializable
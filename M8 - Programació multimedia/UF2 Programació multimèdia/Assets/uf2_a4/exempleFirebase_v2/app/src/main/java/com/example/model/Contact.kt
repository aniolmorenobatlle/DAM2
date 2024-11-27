package com.example.model

data class Contact (
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val position: List<String> = listOf()
)

package com.example.consumindoapijetpackcompose.data

data class Character(
    val fullName: String,
    val nickname: String,
    val hogwartsHouse: String,
    val interpretedBy: String,
    val children: List<String>,
    val image: String,
    val birthdate: String,
) {
    override fun toString(): String {
        return "Characters(fullName='$fullName', nickname='$nickname', hogwartsHouse='$hogwartsHouse', interpretedBy='$interpretedBy', children=$children, image='$image', birthdate='$birthdate')"
    }
}

package com.example.projmobile.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritos")
data class Favorito(
    @PrimaryKey val nome_oficial: String,
    val bairro: String,
    val especialidade: String
)

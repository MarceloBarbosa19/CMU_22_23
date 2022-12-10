package pt.ipp.estg.assistenteviagens.room.UserDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val email: String,
    val fullName: String,
    val password: String,
)

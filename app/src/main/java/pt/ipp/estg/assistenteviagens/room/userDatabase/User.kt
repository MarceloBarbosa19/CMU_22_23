package pt.ipp.estg.assistenteviagens.room.userDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val email: String,
    val fullName: String,
    val password: String,
)

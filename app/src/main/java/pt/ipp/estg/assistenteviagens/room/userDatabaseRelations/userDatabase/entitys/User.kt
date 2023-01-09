package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.userDatabase.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    //val imgUrl: String
    @PrimaryKey val email: String,
    val fullName: String,
    val description: String,
    val password: String,
    val isLogin: Boolean
)

package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey
    val fav_Id: Int,
    val email: String,
    val name: String
)

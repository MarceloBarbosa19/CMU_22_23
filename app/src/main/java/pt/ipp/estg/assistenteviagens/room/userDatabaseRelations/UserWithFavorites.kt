package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations

import androidx.room.Embedded
import androidx.room.Relation
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase.entitys.Favorite
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.userDatabase.entitys.User

data class UserWithFavorites(
    @Embedded val user: User,
    @Relation(
        parentColumn = "email",
        entityColumn = "fav_Id",
    )
    val cars: List<Favorite>,
)

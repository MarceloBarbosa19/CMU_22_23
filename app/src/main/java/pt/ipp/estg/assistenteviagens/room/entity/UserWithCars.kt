package pt.ipp.estg.assistenteviagens.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithCars(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "userCreator_Id"
    )
    val cars: List<Car>
)

package pt.ipp.estg.assistenteviagens.room.userDatabase.entitys

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithCars (
    @Embedded val user: User,
    @Relation(
        parentColumn = "email",
        entityColumn = "car_Id",
    )
    val cars: List<Car>,
)
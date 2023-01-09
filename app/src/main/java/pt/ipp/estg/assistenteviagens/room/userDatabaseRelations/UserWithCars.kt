package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations

import androidx.room.Embedded
import androidx.room.Relation
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase.entitys.Car
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.userDatabase.entitys.User

data class UserWithCars (
    @Embedded val user: User,
    @Relation(
        parentColumn = "email",
        entityColumn = "car_Id",
    )
    val cars: List<Car>,
)
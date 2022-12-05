package pt.ipp.estg.assistenteviagens.room.entity

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation

data class UserWithCars(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "userCreator_Id"
    )
    val cars: LiveData<List<Car>>
)

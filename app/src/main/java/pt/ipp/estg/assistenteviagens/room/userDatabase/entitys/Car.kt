package pt.ipp.estg.assistenteviagens.room.userDatabase.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey
    val car_Id: Int,
    val car_Brand: String,
    val car_Fuel: String,
)

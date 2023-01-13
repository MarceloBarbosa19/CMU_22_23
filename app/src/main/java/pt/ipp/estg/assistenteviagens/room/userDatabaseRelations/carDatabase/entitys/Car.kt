package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true)
    var car_Id: Int = 0,
    val car_Brand: String,
    val email: String,
    val car_Fuel: String,
)

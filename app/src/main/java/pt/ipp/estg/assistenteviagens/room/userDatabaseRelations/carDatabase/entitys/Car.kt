package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey
    val car_Brand: String,
    val car_Fuel: String,
){
    //@PrimaryKey(autoGenerate = true)
    //var car_Id: Int = 0
}

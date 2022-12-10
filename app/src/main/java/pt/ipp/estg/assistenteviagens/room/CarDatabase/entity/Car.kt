package pt.ipp.estg.assistenteviagens.room.CarDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    val userCreator_Id: Int,
    val name: String,
    val gasType: String,
) {
    @PrimaryKey(autoGenerate = true)
    var car_id: Int = 0
}

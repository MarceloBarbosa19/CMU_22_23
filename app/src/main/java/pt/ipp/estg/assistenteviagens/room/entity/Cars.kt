package pt.ipp.estg.assistenteviagens.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cars(
    val name: String,
    val gasType: String,
) {
    @PrimaryKey(autoGenerate = true)
    var car_id: Int = 0
}

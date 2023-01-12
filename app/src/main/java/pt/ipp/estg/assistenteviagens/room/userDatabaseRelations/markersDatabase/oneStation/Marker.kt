package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Marker(
    @PrimaryKey
    val nome: String,
    val latitude: Double,
    val longitude: Double,
)
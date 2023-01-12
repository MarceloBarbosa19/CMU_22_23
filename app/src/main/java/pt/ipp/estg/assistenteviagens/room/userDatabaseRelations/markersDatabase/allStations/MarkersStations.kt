package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.allStations

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarkersStations(
    @PrimaryKey
    val nome: String,
    val latitude: Double,
    val longitude: Double,
)

package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsSearch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationsSearchDB(
    @PrimaryKey
    val Id: Int,
    val Nome: String,
)

package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.districts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DistrictsDB(
    @PrimaryKey
    val Id: Int,
    val Descritivo: String,
)

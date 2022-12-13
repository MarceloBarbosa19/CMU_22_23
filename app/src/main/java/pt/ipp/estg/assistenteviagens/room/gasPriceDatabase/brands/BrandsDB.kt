package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.brands

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BrandsDB (
    @PrimaryKey
    val Id: Int,
    val Descritivo: String,
)
package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.countys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountysDB(
    @PrimaryKey
    val Id: Int,
    val Descritivo: String,
    val IdDistrito: Int,
)

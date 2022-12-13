package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GasTypeDB(
    @PrimaryKey
    val Id: Int,
    val Descritivo: String,
    val UnidadeMedida: String,
)

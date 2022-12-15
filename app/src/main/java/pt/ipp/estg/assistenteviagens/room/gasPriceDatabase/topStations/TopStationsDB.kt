package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.topStations

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopStationsDB(
    @PrimaryKey
    val Id: Int,
    val Nome: String,
    val Preco: String,
    val Combustivel: String,
)

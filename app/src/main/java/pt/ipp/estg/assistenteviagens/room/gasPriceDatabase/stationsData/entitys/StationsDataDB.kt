package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationsDataDB(
    @PrimaryKey
    val Nome: String,
    val Marca: String,
    val Morada: String,
    val codPostal: String,
    val municipio: String,
    val Distrito: String,
    val Latitude: Double,
    val Longitude: Double,
    val diasUteis: String,
    val sabado: String,
    val domingo: String,
    val feriado: String,
    val MeiosPagamento: String,
    val tipoCombustivel: String,
    val preco: String
)

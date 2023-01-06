package pt.ipp.estg.assistenteviagens.retrofit.stationsData

data class StationsData(
    val Nome: String,
    val Marca: String,
    val Morada: Address,
    val HorarioPosto: Schedules,
    val MeiosPagamento: List<PaymentOptions>,
    val Combustiveis: List<FuelsTypes>,
)

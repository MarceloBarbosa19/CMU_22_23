package pt.ipp.estg.assistenteviagens.retrofit.stationsData

data class ResponseStationsData(
    val status: Boolean,
    val mensagem: String,
    val resultado: StationsData,
)

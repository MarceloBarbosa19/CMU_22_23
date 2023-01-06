package pt.ipp.estg.assistenteviagens.retrofit.stationsSearch

data class ResponseStationsSearch(
    val status: Boolean,
    val mensagem: String,
    val resultado: List<StationsSearch>,
)

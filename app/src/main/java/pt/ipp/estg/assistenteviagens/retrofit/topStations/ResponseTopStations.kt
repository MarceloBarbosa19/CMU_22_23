package pt.ipp.estg.assistenteviagens.retrofit.topStations

data class ResponseTopStations(
    val status: Boolean,
    val mensagem: String,
    val resultado: List<TopStations>,
)

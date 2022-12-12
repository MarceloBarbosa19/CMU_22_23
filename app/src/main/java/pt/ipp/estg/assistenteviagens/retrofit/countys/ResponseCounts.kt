package pt.ipp.estg.assistenteviagens.retrofit.countys

data class ResponseCounts(
    val status: Boolean,
    val mensagem: String,
    val resultado: List<Countys>,
)

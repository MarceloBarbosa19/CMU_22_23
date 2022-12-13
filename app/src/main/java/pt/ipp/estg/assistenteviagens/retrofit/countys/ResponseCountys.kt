package pt.ipp.estg.assistenteviagens.retrofit.countys

data class ResponseCountys(
    val status: Boolean,
    val mensagem: String,
    val resultado: List<Countys>,
)

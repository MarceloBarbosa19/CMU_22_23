package pt.ipp.estg.assistenteviagens.retrofit.gasType

data class ResponseGasType(
    val status: Boolean,
    val mensagem: String,
    val resultado: List<GasType>,
)

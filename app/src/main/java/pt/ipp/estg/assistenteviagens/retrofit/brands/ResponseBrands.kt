package pt.ipp.estg.assistenteviagens.retrofit.brands

data class ResponseBrands(
    val status: Boolean,
    val mensagem: String,
    val resultado: List<Brands>,
)

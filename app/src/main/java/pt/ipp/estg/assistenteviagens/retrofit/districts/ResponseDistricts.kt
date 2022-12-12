package pt.ipp.estg.assistenteviagens.retrofit.districts

data class ResponseDistricts(
    val status: Boolean,
    val mensagem: String,
    val resultado: List<Districts>,
)

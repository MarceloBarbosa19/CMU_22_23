package pt.ipp.estg.assistenteviagens.retrofit

import pt.ipp.estg.assistenteviagens.retrofit.brands.ResponseBrands
import pt.ipp.estg.assistenteviagens.retrofit.countys.ResponseCounts
import pt.ipp.estg.assistenteviagens.retrofit.districts.ResponseDistricts
import pt.ipp.estg.assistenteviagens.retrofit.gasType.ResponseGasType
import retrofit2.Response
import retrofit2.http.GET

interface RoutesAPI {
    @GET("GetTiposCombustiveis")
    suspend fun getGasTypes(): Response<ResponseGasType>

    @GET("GetMarcas")
    suspend fun getBrands(): Response<ResponseBrands>

    @GET("GetDistritos")
    suspend fun getDistricts(): Response<ResponseDistricts>

    @GET("GetMunicipios")
    suspend fun getCountys(): Response<ResponseCounts>
}
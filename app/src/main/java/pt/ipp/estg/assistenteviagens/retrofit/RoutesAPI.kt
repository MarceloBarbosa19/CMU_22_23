package pt.ipp.estg.assistenteviagens.retrofit

import pt.ipp.estg.assistenteviagens.retrofit.brands.ResponseBrands
import pt.ipp.estg.assistenteviagens.retrofit.countys.ResponseCountys
import pt.ipp.estg.assistenteviagens.retrofit.districts.ResponseDistricts
import pt.ipp.estg.assistenteviagens.retrofit.gasType.ResponseGasType
import pt.ipp.estg.assistenteviagens.retrofit.stationsData.ResponseStationsData
import pt.ipp.estg.assistenteviagens.retrofit.stationsSearch.ResponseStationsSearch
import pt.ipp.estg.assistenteviagens.retrofit.topStations.ResponseTopStations
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RoutesAPI {
    @GET("GetTiposCombustiveis")
    suspend fun getGasTypesRoute(): Response<ResponseGasType>

    @GET("GetMarcas")
    suspend fun getBrandsRoute(): Response<ResponseBrands>

    @GET("GetDistritos")
    suspend fun getDistrictsRoute(): Response<ResponseDistricts>

    @GET("GetMunicipios")
    suspend fun getCountysRoute(): Response<ResponseCountys>

    @GET("ListarTopPostos")
    suspend fun getTopStationsList(
        @Query("idsTiposComb") idsComb: Int,
        @Query("qtdPorPagina") qtd: Int,
    ):Response<ResponseTopStations>

    @GET("GetDadosPosto?")
    suspend fun getStationsData(
        @Query("id") id: Int,
    ):Response<ResponseStationsData>

    @GET("ListarDadosPostos?")
    suspend fun getStationsList(
        @Query("idsTiposComb") idsTiposComb: Int,
        @Query("idMarca") idMarca: Int,
        @Query("idDistrito") idDistrito: Int,
        @Query("idsMunicipios") idsMunicipios: Int,
    ):Response<ResponseStationsSearch>
}
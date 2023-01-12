package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsSearch

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI
import pt.ipp.estg.assistenteviagens.retrofit.stationsSearch.ResponseStationsSearch
import retrofit2.Response

class StationsSearchRepository(val stationsSearchDao: StationsSearchDao, val stationsSearchAPI: RoutesAPI) {
    fun getStationsSearch(): LiveData<List<StationsSearchDB>> {
        return stationsSearchDao.getStationsSearch()
    }

    suspend fun updateStationsSearchOnline(
        idsTiposComb: Int,
        idMarca: Int,
        idDistrito: Int,
        idsMunicipios: Int
    ): Response<ResponseStationsSearch> {
        return this.stationsSearchAPI.getStationsList(idsTiposComb, idMarca, idDistrito, idsMunicipios)
    }

    suspend fun updateStationsSearchOnline(idsMunicipios: Int): Response<ResponseStationsSearch> {
        return this.stationsSearchAPI.getAllStationsList(idsMunicipios)
    }

    suspend fun insertStationsSearch(pesquisa: StationsSearchDB) {
        stationsSearchDao.insertStationsSearch(pesquisa)
    }

    suspend fun deleteAllStationsSearch(){
        stationsSearchDao.deleteAllStationsSearch()
    }
}
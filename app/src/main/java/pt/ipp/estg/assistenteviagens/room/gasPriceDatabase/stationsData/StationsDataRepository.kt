package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI
import pt.ipp.estg.assistenteviagens.retrofit.stationsData.ResponseStationsData
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData.entitys.StationsDataDB
import retrofit2.Response

class StationsDataRepository(val stationsDataDao: StationsDataDao, val stationsDataAPI: RoutesAPI) {
    fun getStationsData(): LiveData<StationsDataDB> {
        return stationsDataDao.getStationsData()
    }

    suspend fun updateStationsDataOnline(id: Int): Response<ResponseStationsData> {
        return this.stationsDataAPI.getStationsData(id)
    }

    fun getStationsDataByName(name: String): LiveData<StationsDataDB> {
        return stationsDataDao.getStationsDataByName(name)
    }

    suspend fun insertStationsData(info: StationsDataDB) {
        stationsDataDao.insertStationsData(info)
    }

    suspend fun deleteAllStationsData() {
        stationsDataDao.deleteAllStationsData()
    }
}
package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.countys

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI
import pt.ipp.estg.assistenteviagens.retrofit.countys.ResponseCountys
import retrofit2.Response

class CountysRepository(val countysDao: CountysDao, val countysAPI: RoutesAPI) {

    fun getCountys(): LiveData<List<CountysDB>> {
        return countysDao.getCountys()
    }

    suspend fun updateCountysOnline(): Response<ResponseCountys> {
        return this.countysAPI.getCountysRoute()
    }

    suspend fun insertCountys(county: CountysDB) {
        countysDao.insertCountys(county)
    }
}
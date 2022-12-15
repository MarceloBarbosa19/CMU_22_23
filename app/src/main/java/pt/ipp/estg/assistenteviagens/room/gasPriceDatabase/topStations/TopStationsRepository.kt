package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.topStations

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI
import pt.ipp.estg.assistenteviagens.retrofit.topStations.ResponseTopStations
import retrofit2.Response

class TopStationsRepository(val topStationsDao: TopStationsDao, val topStationsAPI: RoutesAPI) {

    fun getTopStations(): LiveData<List<TopStationsDB>> {
        return topStationsDao.getTopStation()
    }

    suspend fun updateTopStationsOnline(id: Int): Response<ResponseTopStations> {
        return this.topStationsAPI.getListaTopPostos(id, 5)
    }

    suspend fun insertTopStations(topStations: TopStationsDB){
        topStationsDao.insertTopStations(topStations)
    }

}
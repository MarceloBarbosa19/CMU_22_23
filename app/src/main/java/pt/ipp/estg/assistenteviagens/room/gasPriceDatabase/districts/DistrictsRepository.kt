package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.districts

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI
import pt.ipp.estg.assistenteviagens.retrofit.districts.ResponseDistricts
import retrofit2.Response

class DistrictsRepository(val districtsDao: DistrictsDao, val districtsAPI: RoutesAPI) {

    fun getDistrict(): LiveData<List<DistrictsDB>> {
        return districtsDao.getDistricts()
    }

    suspend fun updateDistrictsOnline(): Response<ResponseDistricts> {
        return this.districtsAPI.getDistrictsRoute()
    }

    suspend fun insertDistrict(districts: DistrictsDB){
        districtsDao.insertDistricts(districts)
    }
}
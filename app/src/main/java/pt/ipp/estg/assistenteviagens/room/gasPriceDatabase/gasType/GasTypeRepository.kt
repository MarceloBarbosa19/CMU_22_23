package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI
import pt.ipp.estg.assistenteviagens.retrofit.gasType.ResponseGasType
import retrofit2.Response

class GasTypeRepository(val gasTypeDao: GasTypeDao, val gasTypeAPI: RoutesAPI) {
    fun getGasType(): LiveData<List<GasTypeDB>> {
        return gasTypeDao.getGasType()
    }

    suspend fun updateGasTypeOnline(): Response<ResponseGasType> {
        return this.gasTypeAPI.getGasTypesRoute()
    }

    suspend fun insertGas(gasType: GasTypeDB) {
        gasTypeDao.insertGas(gasType)
    }
}
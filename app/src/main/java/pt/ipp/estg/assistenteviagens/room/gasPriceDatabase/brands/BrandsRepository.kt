package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.brands

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.retrofit.RoutesAPI
import pt.ipp.estg.assistenteviagens.retrofit.brands.ResponseBrands
import retrofit2.Response

class BrandsRepository (val brandsDao: BrandsDao, val brandAPI: RoutesAPI) {
    fun getBrands(): LiveData<List<BrandsDB>> {
        return brandsDao.getBrands()
    }

    suspend fun updateBrandsOnline(): Response<ResponseBrands> {
        return this.brandAPI.getBrandsRoute()
    }

    suspend fun insertBrands(brand: BrandsDB){
        return brandsDao.insertBrand(brand)
    }
}
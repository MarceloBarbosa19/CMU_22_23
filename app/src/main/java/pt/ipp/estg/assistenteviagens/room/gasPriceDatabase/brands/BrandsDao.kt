package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.brands

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BrandsDao {
    @Query("select * from BrandsDB")
    fun getBrands(): LiveData<List<BrandsDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBrand(brand: BrandsDB)
}
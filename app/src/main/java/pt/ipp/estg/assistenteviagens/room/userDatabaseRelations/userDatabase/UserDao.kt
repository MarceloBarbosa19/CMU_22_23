package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.userDatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.userDatabase.entitys.User

@Dao
interface UserDao {
    @Query("select * from User")
    fun loadUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}
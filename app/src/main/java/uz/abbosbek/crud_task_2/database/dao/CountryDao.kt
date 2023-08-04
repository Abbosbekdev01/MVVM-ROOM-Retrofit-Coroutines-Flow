package uz.abbosbek.crud_task_2.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.abbosbek.crud_task_2.database.entity.CountryEntity
@Dao
interface CountryDao {

    @Insert
    fun addUserFlag(userEntity:CountryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsersFlags(list: List<CountryEntity>)

    @Query("select * from countryentity")
    fun getUsersFlags(): List<CountryEntity>

    @Query("select count(*) from countryentity")
    fun getUsersCountFlag(): Int
}
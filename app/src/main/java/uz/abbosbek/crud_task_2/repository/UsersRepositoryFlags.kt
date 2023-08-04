package uz.abbosbek.crud_task_2.repository

import kotlinx.coroutines.flow.flow
import uz.abbosbek.crud_task_2.database.dao.CountryDao
import uz.abbosbek.crud_task_2.database.entity.CountryEntity
import uz.abbosbek.crud_task_2.networking.ApiService

class UsersRepositoryFlags(private val apiService: ApiService, private val userDao: CountryDao) {

    //todo: API dan ma'lumotlarni olin kelib beradi
    fun getUserFlag() = apiService.getAllFlag()

    //todo: Ma'lumotlarni Databasega qo'shyabmiz
    fun addUserFlags(list: List<CountryEntity>) = flow { emit(userDao.addUsersFlags(list)) }

    fun getDatabaseUsersFlags() = userDao.getUsersFlags()

    fun getUserCountFlags() = userDao.getUsersCountFlag()

}
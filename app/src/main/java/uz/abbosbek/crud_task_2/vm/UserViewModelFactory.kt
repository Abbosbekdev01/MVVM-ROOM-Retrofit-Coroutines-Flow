package uz.abbosbek.crud_task_2.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.crud_task_2.database.AppDatabase
import uz.abbosbek.crud_task_2.networking.ApiService
import uz.abbosbek.crud_task_2.utils.NetworkHelper
import java.lang.Exception

class UserViewModelFactory(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService,
    private val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(appDatabase, apiService, networkHelper) as T
        }
        return throw Exception("Error")
    }
}
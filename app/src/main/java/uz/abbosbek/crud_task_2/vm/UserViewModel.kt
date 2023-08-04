package uz.abbosbek.crud_task_2.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import uz.abbosbek.crud_task_2.database.AppDatabase
import uz.abbosbek.crud_task_2.database.entity.CountryEntity
import uz.abbosbek.crud_task_2.mepper.mapToEntity
import uz.abbosbek.crud_task_2.networking.ApiService
import uz.abbosbek.crud_task_2.repository.UsersRepositoryFlags
import uz.abbosbek.crud_task_2.utils.NetworkHelper


//todo: View Model
class UserViewModel(
    appDatabase: AppDatabase,
    apiService: ApiService, // almashtirib qo'ygaman
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val userRepository = UsersRepositoryFlags(apiService, appDatabase.userDaoFlag())
    private val stateFlow = MutableStateFlow<Resource<List<CountryEntity>>>(Resource.Loading())

    init {
        fetchUsersFlags()
    }

    private fun fetchUsersFlags() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnection()) {
                userRepository.getUserFlag()
                    .catch {
                        stateFlow.emit(Resource.Error(it))
                    }.flatMapConcat {
                        val list = ArrayList<CountryEntity>()
                        list.clear()
                        it.forEach { userData ->
                            list.add(userData.mapToEntity())
                        }
                        userRepository.addUserFlags(list)
                    }.collect {
                        stateFlow.emit(Resource.Success(userRepository.getDatabaseUsersFlags()))
                    }
            } else {
                if (userRepository.getUserCountFlags() > 0) {
                    stateFlow.emit(Resource.Success(userRepository.getDatabaseUsersFlags()))
                } else {
                    stateFlow.emit(Resource.Error(Throwable("Internet not connection")))
                }
            }
        }
    }

    fun getStateFlow(): StateFlow<Resource<List<CountryEntity>>> {
        return stateFlow
    }
}
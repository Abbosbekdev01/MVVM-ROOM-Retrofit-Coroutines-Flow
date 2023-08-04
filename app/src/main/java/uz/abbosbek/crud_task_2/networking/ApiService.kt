package uz.abbosbek.crud_task_2.networking

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import uz.abbosbek.crud_task_2.models.Country

interface ApiService {

    @GET("/v3.1/all")
     fun getAllFlag(): Flow<List<Country>>
}

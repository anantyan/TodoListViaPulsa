package id.anantyan.todolistviapulsa.data.remote.service

import id.anantyan.todolistviapulsa.data.remote.model.RequestModel
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
interface PersonJobsService {
    @GET("PersonJobs")
    suspend fun fetchAll(): Response<List<ResponseModel>>

    @GET("PersonJobs/{id}")
    suspend fun fetch(
        @Path("id") id: String
    ): Response<ResponseModel>

    @POST("PersonJobs")
    suspend fun insert(
        @Body field: RequestModel
    ): Response<ResponseModel>

    @PUT("PersonJobs/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body field: RequestModel
    ): Response<ResponseModel>

    @DELETE("PersonJobs/{id}")
    suspend fun delete(
        @Path("id") id: String
    ): Response<ResponseModel>
}
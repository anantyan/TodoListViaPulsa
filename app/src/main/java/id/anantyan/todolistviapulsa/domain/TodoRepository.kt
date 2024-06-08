package id.anantyan.todolistviapulsa.domain

import id.anantyan.todolistviapulsa.data.remote.model.RequestModel
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import retrofit2.Response

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
interface TodoRepository {
    suspend fun fetchAll(): List<ResponseModel>
    suspend fun fetch(id: String): ResponseModel
    suspend fun post(field: RequestModel): ResponseModel
    suspend fun put(id: String, field: RequestModel): ResponseModel
    suspend fun delete(id: String): ResponseModel
}
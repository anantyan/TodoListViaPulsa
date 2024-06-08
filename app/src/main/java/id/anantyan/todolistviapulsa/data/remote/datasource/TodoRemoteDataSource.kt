package id.anantyan.todolistviapulsa.data.remote.datasource

import id.anantyan.todolistviapulsa.data.remote.model.RequestModel
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
interface TodoRemoteDataSource {
    suspend fun fetchAll(): List<ResponseModel>?
    suspend fun fetch(id: String): ResponseModel?
    suspend fun post(field: RequestModel): ResponseModel?
    suspend fun put(id: String, field: RequestModel): ResponseModel?
    suspend fun delete(id: String): ResponseModel?
}
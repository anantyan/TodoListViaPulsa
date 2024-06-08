package id.anantyan.todolistviapulsa.data.remote.datasource

import id.anantyan.todolistviapulsa.data.remote.model.RequestModel
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import id.anantyan.todolistviapulsa.data.remote.service.PersonJobsService

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
class TodoRemoteDataSourceImpl(
    private val service: PersonJobsService
) : TodoRemoteDataSource {
    private fun specificErrorMessage(code: Int): String {
        return when (code) {
            in 400..499 -> {
                "Terjadi kesalahan!"
            }
            in 500..599 -> {
                "Terjadi kesalahan pada server!"
            }
            else -> "Terjadi kesalahan!"
        }
    }

    override suspend fun fetchAll(): List<ResponseModel>? {
        val response = service.fetchAll()
        return if (response.isSuccessful) {
            response.body()
        } else {
            val code = response.code()
            throw Exception(specificErrorMessage(code))
        }
    }

    override suspend fun fetch(id: String): ResponseModel? {
        val response = service.fetch(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            val code = response.code()
            throw Exception(specificErrorMessage(code))
        }
    }

    override suspend fun post(field: RequestModel): ResponseModel? {
        val response = service.insert(field)
        return if (response.isSuccessful) {
            response.body()
        } else {
            val code = response.code()
            throw Exception(specificErrorMessage(code))
        }
    }

    override suspend fun put(id: String, field: RequestModel): ResponseModel? {
        val response = service.update(id, field)
        return if (response.isSuccessful) {
            response.body()
        } else {
            val code = response.code()
            throw Exception(specificErrorMessage(code))
        }
    }

    override suspend fun delete(id: String): ResponseModel? {
        val response = service.delete(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            val code = response.code()
            throw Exception(specificErrorMessage(code))
        }
    }
}
package id.anantyan.todolistviapulsa.data.repository

import id.anantyan.todolistviapulsa.data.remote.datasource.TodoRemoteDataSource
import id.anantyan.todolistviapulsa.data.remote.model.RequestModel
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import id.anantyan.todolistviapulsa.domain.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
class TodoRepositoryImpl(
    private val todoRemoteDataSource: TodoRemoteDataSource,
): TodoRepository {
    override suspend fun fetchAll(): List<ResponseModel> {
        return try {
            val response = todoRemoteDataSource.fetchAll()
            withContext(Dispatchers.IO) {
                response ?: emptyList()
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun fetch(id: String): ResponseModel {
        return try {
            val response = todoRemoteDataSource.fetch(id)
            withContext(Dispatchers.IO) {
                response ?: ResponseModel()
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun post(field: RequestModel): ResponseModel {
        return try {
            val response = todoRemoteDataSource.post(field)
            withContext(Dispatchers.IO) {
                response ?: ResponseModel()
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun put(id: String, field: RequestModel): ResponseModel {
        return try {
            val response = todoRemoteDataSource.put(id, field)
            withContext(Dispatchers.IO) {
                response ?: ResponseModel()
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun delete(id: String): ResponseModel {
        return try {
            val response = todoRemoteDataSource.delete(id)
            withContext(Dispatchers.IO) {
                response ?: ResponseModel()
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}
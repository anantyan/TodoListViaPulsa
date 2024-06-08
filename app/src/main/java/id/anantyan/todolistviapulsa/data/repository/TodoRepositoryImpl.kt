package id.anantyan.todolistviapulsa.data.repository

import id.anantyan.todolistviapulsa.data.remote.datasource.TodoRemoteDataSource
import id.anantyan.todolistviapulsa.data.remote.model.RequestModel
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import id.anantyan.todolistviapulsa.domain.TodoRepository

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
class TodoRepositoryImpl(
    private val todoRemoteDataSource: TodoRemoteDataSource,
): TodoRepository {
    override suspend fun fetchAll(): List<ResponseModel> {
        TODO("Not yet implemented")
    }

    override suspend fun fetch(id: String): ResponseModel {
        TODO("Not yet implemented")
    }

    override suspend fun post(field: RequestModel): ResponseModel {
        TODO("Not yet implemented")
    }

    override suspend fun put(id: String, field: RequestModel): ResponseModel {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): ResponseModel {
        TODO("Not yet implemented")
    }
}
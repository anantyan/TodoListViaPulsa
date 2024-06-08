package id.anantyan.todolistviapulsa.domain

import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
class TodoFetchUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(id: String) = todoRepository.fetch(id)
}
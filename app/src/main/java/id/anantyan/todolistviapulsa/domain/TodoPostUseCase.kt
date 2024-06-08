package id.anantyan.todolistviapulsa.domain

import id.anantyan.todolistviapulsa.data.remote.model.RequestModel

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
class TodoPostUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(field: RequestModel) = todoRepository.post(field)
}
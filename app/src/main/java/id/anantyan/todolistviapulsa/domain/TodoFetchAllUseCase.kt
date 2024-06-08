package id.anantyan.todolistviapulsa.domain

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
class TodoFetchAllUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke() = todoRepository.fetchAll()
}
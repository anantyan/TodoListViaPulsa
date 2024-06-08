package id.anantyan.todolistviapulsa.domain

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
class TodoDeleteUseCase(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(id: String) = todoRepository.delete(id)
}
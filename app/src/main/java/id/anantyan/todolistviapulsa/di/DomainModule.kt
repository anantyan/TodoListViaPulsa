package id.anantyan.todolistviapulsa.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.anantyan.todolistviapulsa.domain.TodoDeleteUseCase
import id.anantyan.todolistviapulsa.domain.TodoFetchAllUseCase
import id.anantyan.todolistviapulsa.domain.TodoFetchUseCase
import id.anantyan.todolistviapulsa.domain.TodoPostUseCase
import id.anantyan.todolistviapulsa.domain.TodoPutUseCase
import id.anantyan.todolistviapulsa.domain.TodoRepository
import javax.inject.Singleton

/**
 * Created by Arya Rezza Anantya on 08/06/24.
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideTodoFetchAllUseCase(
        todoRepository: TodoRepository
    ): TodoFetchAllUseCase {
        return TodoFetchAllUseCase(todoRepository)
    }

    @Singleton
    @Provides
    fun provideTodoFetchUseCase(
        todoRepository: TodoRepository
    ): TodoFetchUseCase {
        return TodoFetchUseCase(todoRepository)
    }

    @Singleton
    @Provides
    fun provideTodoPostUseCase(
        todoRepository: TodoRepository
    ): TodoPostUseCase {
        return TodoPostUseCase(todoRepository)
    }

    @Singleton
    @Provides
    fun provideTodoPutUseCase(
        todoRepository: TodoRepository
    ): TodoPutUseCase {
        return TodoPutUseCase(todoRepository)
    }

    @Singleton
    @Provides
    fun provideTodoDeleteUseCase(
        todoRepository: TodoRepository
    ): TodoDeleteUseCase {
        return TodoDeleteUseCase(todoRepository)
    }
}
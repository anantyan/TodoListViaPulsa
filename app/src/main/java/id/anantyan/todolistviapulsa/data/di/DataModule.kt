package id.anantyan.todolistviapulsa.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.anantyan.todolistviapulsa.data.remote.datasource.TodoRemoteDataSource
import id.anantyan.todolistviapulsa.data.remote.datasource.TodoRemoteDataSourceImpl
import id.anantyan.todolistviapulsa.data.remote.service.PersonJobsService
import id.anantyan.todolistviapulsa.data.repository.TodoRepositoryImpl
import id.anantyan.todolistviapulsa.domain.TodoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideTodoRemoteDataSourceImpl(
        personJobsService: PersonJobsService
    ): TodoRemoteDataSource {
        return TodoRemoteDataSourceImpl(personJobsService)
    }

    @Singleton
    @Provides
    fun provideTodoRepositoryImpl(
        todoRemoteDataSource: TodoRemoteDataSource
    ): TodoRepository {
        return TodoRepositoryImpl(todoRemoteDataSource)
    }
}
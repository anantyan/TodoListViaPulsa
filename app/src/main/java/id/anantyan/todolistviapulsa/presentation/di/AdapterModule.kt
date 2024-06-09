package id.anantyan.todolistviapulsa.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import id.anantyan.todolistviapulsa.presentation.ui.list.ListAdapter

@Module
@InstallIn(ActivityComponent::class)
object AdapterModule {
    @Provides
    fun provideListAdapter(): ListAdapter {
        return ListAdapter()
    }
}
package id.anantyan.todolistviapulsa

import id.anantyan.todolistviapulsa.data.remote.datasource.TodoRemoteDataSourceImplTest
import id.anantyan.todolistviapulsa.data.repository.TodoRepositoryImplTest
import id.anantyan.todolistviapulsa.presentation.ui.list.ListViewModelTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    TodoRemoteDataSourceImplTest::class,
    TodoRepositoryImplTest::class,
    ListViewModelTest::class
)
class MainUnitTestSuite
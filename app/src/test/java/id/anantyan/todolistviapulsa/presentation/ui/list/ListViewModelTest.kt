package id.anantyan.todolistviapulsa.presentation.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.anantyan.todolistviapulsa.common.MainDispatcherRule
import id.anantyan.todolistviapulsa.common.State
import id.anantyan.todolistviapulsa.common.getOrAwaitValue
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import id.anantyan.todolistviapulsa.domain.TodoDeleteUseCase
import id.anantyan.todolistviapulsa.domain.TodoFetchAllUseCase
import id.anantyan.todolistviapulsa.domain.TodoFetchUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule: MainDispatcherRule = MainDispatcherRule()

    private lateinit var todoFetchAllUseCase: TodoFetchAllUseCase
    private lateinit var todoFetchUseCase: TodoFetchUseCase
    private lateinit var todoDeleteUseCase: TodoDeleteUseCase
    private lateinit var viewModel: ListViewModel

    @Before
    fun setUp() {
        todoFetchAllUseCase = mockk()
        todoFetchUseCase = mockk()
        todoDeleteUseCase = mockk()
        viewModel = ListViewModel(todoFetchAllUseCase, todoFetchUseCase, todoDeleteUseCase)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `List ViewModel - fetchAll success`() = runTest {
        val mockResponse = listOf<ResponseModel>(mockk())
        coEvery { todoFetchAllUseCase.invoke() } returns mockResponse

        viewModel.fetchAll()

        val value = viewModel.fetchAll.getOrAwaitValue()
        assertTrue(value is State.Success)
        assertEquals(State.Success(mockResponse).data, value.data)

        coVerify { todoFetchAllUseCase.invoke() }
    }

    @Test
    fun `List ViewModel - fetchAll error`() = runTest {
        val errorMessage = "Error fetching all"
        coEvery { todoFetchAllUseCase.invoke() } throws Exception(errorMessage)

        viewModel.fetchAll()

        val value = viewModel.fetchAll.getOrAwaitValue()
        assertTrue(value is State.Error)
        assertEquals(State.Error<List<ResponseModel>>(null, errorMessage).message, value.message)

        coVerify { todoFetchAllUseCase.invoke() }
    }

    @Test
    fun `List ViewModel - fetch success`() = runTest {
        val mockResponse = mockk<ResponseModel>()
        coEvery { todoFetchUseCase.invoke("1") } returns mockResponse

        viewModel.fetch("1")

        val value = viewModel.fetch.getOrAwaitValue()
        assertTrue(value is State.Success)
        assertEquals(State.Success(mockResponse).data, value.data)

        coVerify { todoFetchUseCase.invoke("1") }
    }

    @Test
    fun `List ViewModel - fetch error`() = runTest {
        val errorMessage = "Error fetching"
        coEvery { todoFetchUseCase.invoke("1") } throws Exception(errorMessage)

        viewModel.fetch("1")

        val value = viewModel.fetch.getOrAwaitValue()
        assertTrue(value is State.Error)
        assertEquals(State.Error<ResponseModel>(null, errorMessage).message, value.message)

        coVerify { todoFetchUseCase.invoke("1") }
    }

    @Test
    fun `List ViewModel - delete success`() = runTest {
        val mockResponse = mockk<ResponseModel>()
        coEvery { todoDeleteUseCase.invoke("1") } returns mockResponse

        viewModel.delete("1")

        val value = viewModel.delete.getOrAwaitValue()
        assertTrue(value is State.Success)
        assertEquals(State.Success(mockResponse).data, value.data)

        coVerify { todoDeleteUseCase.invoke("1") }
    }

    @Test
    fun `List ViewModel - delete error`() = runTest {
        val errorMessage = "Error deleting"
        coEvery { todoDeleteUseCase.invoke("1") } throws Exception(errorMessage)

        viewModel.delete("1")

        val value = viewModel.delete.getOrAwaitValue()
        assertTrue(value is State.Error)
        assertEquals(State.Error<ResponseModel>(null, errorMessage).message, value.message)

        coVerify { todoDeleteUseCase.invoke("1") }
    }
}
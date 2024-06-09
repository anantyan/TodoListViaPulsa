package id.anantyan.todolistviapulsa.data.repository

import id.anantyan.todolistviapulsa.data.remote.datasource.TodoRemoteDataSource
import id.anantyan.todolistviapulsa.data.remote.model.RequestModel
import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class TodoRepositoryImplTest {

    private lateinit var remoteDataSource: TodoRemoteDataSource
    private lateinit var repository: TodoRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteDataSource = mockk()
        repository = TodoRepositoryImpl(remoteDataSource)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Todo Repository - fetchAll success`() = runBlocking {
        val mockResponse = listOf<ResponseModel>(mockk(), mockk())

        coEvery { remoteDataSource.fetchAll() } returns mockResponse

        val result = withContext(Dispatchers.IO) { repository.fetchAll() }
        assertNotNull(result)
        assertEquals(2, result.size)
    }

    @Test
    fun `Todo Repository - fetchAll empty response`() = runBlocking {
        coEvery { remoteDataSource.fetchAll() } returns null

        val result = withContext(Dispatchers.IO) { repository.fetchAll() }
        assertNotNull(result)
        assertTrue(result.isEmpty())
    }

    @Test(expected = Exception::class)
    fun `Todo Repository - fetchAll error`(): Unit = runBlocking {
        coEvery { remoteDataSource.fetchAll() } throws Exception("Error fetching all")

        withContext(Dispatchers.IO) { repository.fetchAll() }
    }

    @Test
    fun `Todo Repository - fetch success`() = runBlocking {
        val mockResponse = mockk<ResponseModel>()

        coEvery { remoteDataSource.fetch("1") } returns mockResponse

        val result = withContext(Dispatchers.IO) { repository.fetch("1") }
        assertNotNull(result)
        assertEquals(mockResponse, result)
    }

    @Test
    fun `Todo Repository - fetch empty response`() = runBlocking {
        coEvery { remoteDataSource.fetch("1") } returns null

        val result = withContext(Dispatchers.IO) { repository.fetch("1") }
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `Todo Repository - fetch error`(): Unit = runBlocking {
        coEvery { remoteDataSource.fetch("1") } throws Exception("Error fetching")

        withContext(Dispatchers.IO) { repository.fetch("1") }
    }

    @Test
    fun `Todo Repository - post success`() = runBlocking {
        val mockResponse = mockk<ResponseModel>()
        val request = mockk<RequestModel>()

        coEvery { remoteDataSource.post(request) } returns mockResponse

        val result = withContext(Dispatchers.IO) { repository.post(request) }
        assertNotNull(result)
        assertEquals(mockResponse, result)
    }

    @Test
    fun `Todo Repository - post empty response`() = runBlocking {
        val request = mockk<RequestModel>()

        coEvery { remoteDataSource.post(request) } returns null

        val result = withContext(Dispatchers.IO) { repository.post(request) }
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `Todo Repository - post error`(): Unit = runBlocking {
        val request = mockk<RequestModel>()

        coEvery { remoteDataSource.post(request) } throws Exception("Error posting")

        withContext(Dispatchers.IO) { repository.post(request) }
    }

    @Test
    fun `Todo Repository - put success`() = runBlocking {
        val mockResponse = mockk<ResponseModel>()
        val request = mockk<RequestModel>()

        coEvery { remoteDataSource.put("1", request) } returns mockResponse

        val result = withContext(Dispatchers.IO) { repository.put("1", request) }
        assertNotNull(result)
        assertEquals(mockResponse, result)
    }

    @Test
    fun `Todo Repository - put empty response`() = runBlocking {
        val request = mockk<RequestModel>()

        coEvery { remoteDataSource.put("1", request) } returns null

        val result = withContext(Dispatchers.IO) { repository.put("1", request) }
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `Todo Repository - put error`(): Unit = runBlocking {
        val request = mockk<RequestModel>()

        coEvery { remoteDataSource.put("1", request) } throws Exception("Error putting")

        withContext(Dispatchers.IO) { repository.put("1", request) }
    }

    @Test
    fun `Todo Repository - delete success`() = runBlocking {
        val mockResponse = mockk<ResponseModel>()

        coEvery { remoteDataSource.delete("1") } returns mockResponse

        val result = withContext(Dispatchers.IO) { repository.delete("1") }
        assertNotNull(result)
        assertEquals(mockResponse, result)
    }

    @Test
    fun `Todo Repository - delete empty response`() = runBlocking {
        coEvery { remoteDataSource.delete("1") } returns null

        val result = withContext(Dispatchers.IO) { repository.delete("1") }
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `Todo Repository - delete error`(): Unit = runBlocking {
        coEvery { remoteDataSource.delete("1") } throws Exception("Error deleting")

        withContext(Dispatchers.IO) { repository.delete("1") }
    }
}
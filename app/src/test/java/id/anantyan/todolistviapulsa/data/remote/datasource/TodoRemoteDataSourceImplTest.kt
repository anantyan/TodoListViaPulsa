package id.anantyan.todolistviapulsa.data.remote.datasource

import id.anantyan.todolistviapulsa.data.remote.model.ResponseModel
import id.anantyan.todolistviapulsa.data.remote.service.PersonJobsService
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class TodoRemoteDataSourceImplTest {

    private lateinit var service: PersonJobsService
    private lateinit var dataSource: TodoRemoteDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        service = mockk()
        dataSource = TodoRemoteDataSourceImpl(service)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Todo Remote Data Source - fetchAll success`(): Unit = runBlocking {
        val mockkResponse = mockk<Response<List<ResponseModel>>> {
            every { isSuccessful } returns true
            every { body() } returns listOf()
        }

        coEvery { service.fetchAll() } returns mockkResponse

        val result = dataSource.fetchAll()
        assertNotNull(result)
        assertTrue(result?.isEmpty() ?: false)
    }

    @Test(expected = Exception::class)
    fun `Todo Remote Data Source - fetchAll error`(): Unit = runBlocking {
        val mockResponse = mockk<Response<List<ResponseModel>>> {
            every { isSuccessful } returns false
            every { code() } returns 404
        }

        coEvery { service.fetchAll() } returns mockResponse

        dataSource.fetchAll()
    }

    @Test
    fun `Todo Remote Data Source - fetch success`(): Unit = runBlocking {
        val mockResponse = mockk<Response<ResponseModel>> {
            every { isSuccessful } returns true
            every { body() } returns mockk()
        }

        coEvery { service.fetch("1") } returns mockResponse

        val result = dataSource.fetch("1")
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `Todo Remote Data Source - fetch error`(): Unit = runBlocking {
        val mockResponse = mockk<Response<ResponseModel>> {
            every { isSuccessful } returns false
            every { code() } returns 500
        }

        coEvery { service.fetch("1") } returns mockResponse

        dataSource.fetch("1")
    }

    @Test
    fun `Todo Remote Data Source - post success`(): Unit = runBlocking {
        val mockResponse = mockk<Response<ResponseModel>> {
            every { isSuccessful } returns true
            every { body() } returns mockk()
        }

        coEvery { service.insert(any()) } returns mockResponse

        val result = dataSource.post(mockk())
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `Todo Remote Data Source - post error`(): Unit = runBlocking {
        val mockResponse = mockk<Response<ResponseModel>> {
            every { isSuccessful } returns false
            every { code() } returns 400
        }

        coEvery { service.insert(any()) } returns mockResponse

        dataSource.post(mockk())
    }

    @Test
    fun `Todo Remote Data Source - put success`(): Unit = runBlocking {
        val mockResponse = mockk<Response<ResponseModel>> {
            every { isSuccessful } returns true
            every { body() } returns mockk()
        }

        coEvery { service.update("1", any()) } returns mockResponse

        val result = dataSource.put("1", mockk())
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `Todo Remote Data Source - put error`(): Unit = runBlocking {
        val mockResponse = mockk<Response<ResponseModel>> {
            every { isSuccessful } returns false
            every { code() } returns 500
        }

        coEvery { service.update("1", any()) } returns mockResponse

        dataSource.put("1", mockk())
    }

    @Test
    fun `Todo Remote Data Source - delete success`(): Unit = runBlocking {
        val mockResponse = mockk<Response<ResponseModel>> {
            every { isSuccessful } returns true
            every { body() } returns mockk()
        }

        coEvery { service.delete("1") } returns mockResponse

        val result = dataSource.delete("1")
        assertNotNull(result)
    }

    @Test(expected = Exception::class)
    fun `Todo Remote Data Source - delete error`(): Unit = runBlocking {
        val mockResponse = mockk<Response<ResponseModel>> {
            every { isSuccessful } returns false
            every { code() } returns 404
        }

        coEvery { service.delete("1") } returns mockResponse

        dataSource.delete("1")
    }
}
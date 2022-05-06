package com.example.footballfixtures

import com.example.footballfixtures.utils.ErrorStatus
import com.example.footballfixtures.utils.Resource
import com.example.footballfixtures.utils.apiCall
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class ApiCallHandlerKtTest {


    @Test
    fun `apiCall returns success`() = runTest {

        val result = apiCall(testScheduler) {
            "Hello"
        }
        assertIs<Resource.Success<String>>(result)
        assertEquals("Hello", result.value)
    }

    @Test
    fun `apiCall when IOException thrown returns error`() = runTest {
        val result = apiCall(testScheduler) {
            throw IOException()
        }
        assertIs<Resource.Error>(result)
        assertEquals(103, result.code)
        assertEquals("Not connected to the internet", result.error)
    }

    @Test
    fun `apiCall when UnknownHostException thrown returns error`()= runTest{
        val result = apiCall(testScheduler) {
            throw UnknownHostException()
        }
        assertIs<Resource.Error>(result)
        assertEquals(101, result.code)
        assertEquals(ErrorStatus.NO_CONNECTION, result.error)
    }

    @Test
    fun `apiCall when InternalError thrown returns error`() = runTest {
        val result = apiCall(testScheduler) {
            throw InternalError()
        }
        assertIs<Resource.Error>(result)
        assertEquals(500, result.code)
        assertEquals("An internal server error occurred", result.error)
    }

    @Test
    fun `apiCall when SocketTimeoutException thrown returns error`() = runTest {
        val result = apiCall(testScheduler) {
            throw SocketTimeoutException()
        }
        assertIs<Resource.Error>(result)
        assertEquals(102, result.code)
        assertEquals(ErrorStatus.TIMEOUT, result.error)
    }
}
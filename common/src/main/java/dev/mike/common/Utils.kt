/*
package dev.mike.common

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import okio.IOException
import retrofit2.HttpException
import kotlin.Exception


@Serializable
data class ApiError(val message: String)


fun Throwable.parse(): String {
    return when (this){
        is HttpException -> {
            val body = this.response()!!.errorBody()!!.string()
            val error: ApiError = decodeFromString(body)
            return error.message
        }
        is SocketTimeoutException -> "Connection timed out"
        is IOException -> "No connection available"
        else -> this.message!!
    }
}*/

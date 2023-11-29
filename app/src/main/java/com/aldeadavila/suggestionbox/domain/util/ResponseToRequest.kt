package com.aldeadavila.suggestionbox.domain.util

import com.aldeadavila.suggestionbox.domain.model.ErrorResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

object ResponseToRequest {

    fun <T> send(result: Response<T>): Resource<T> {
        return try {
            if (result.isSuccessful) {
                Resource.Succes(result.body()!!)
            } else {
                val errorResponse: ErrorResponse? = ConvertErrorBody.convert(result.errorBody())
                Resource.Failure(errorResponse?.message ?: "Error desconocido")
            }

        }
        catch (e: HttpException) {
            Resource.Failure(e.message ?: "Error desconocido en la petición Http")
        }
        catch (e: IOException) {
            e.printStackTrace()
            Resource.Failure(
                "Verifica tu conexión a Internet")
        }
        catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e.message ?: "Error desconocido")
        }
    }
}
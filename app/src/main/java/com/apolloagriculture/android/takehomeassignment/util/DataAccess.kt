package com.apolloagriculture.android.takehomeassignment.util

import com.apolloagriculture.android.takehomeassignment.model.ErrorMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

suspend fun <T : Any> dataAccess(call: suspend () -> Response<T>) =

    try {
        val response = call()
        val body = response.body()

        if (response.isSuccessful && body != null) {
            Resource.Success(response.body())
        } else {
            val gson = Gson()
            val type = object : TypeToken<ErrorMessage>() {}.type
            val errorResponse: ErrorMessage =
                gson.fromJson(response.errorBody()!!.charStream(), type)
            Resource.Error(message = errorResponse.message)
        }
    } catch (e: Throwable) {
        e.cause?.message?.let { Resource.Error(it) }
    }



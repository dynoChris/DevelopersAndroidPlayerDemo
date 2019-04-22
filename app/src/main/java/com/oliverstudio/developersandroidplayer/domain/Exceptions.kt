package com.oliverstudio.developersandroidplayer.domain

import android.util.Log
import retrofit2.HttpException
import java.io.IOException

/**
 * @author BRcJu
 * @since 22.04.2019
 */


const val BAD_REQUEST_CODE = 400L
const val AUTH_CODE = 401L
const val AUTH_NO_DATA = 404L

fun Throwable.isNoNetwork() = this is IOException

fun Throwable.getExceptionMessage() = when (this) {
    is HttpException -> {
        when {
            //invalid token
            code().toLong() == AUTH_CODE -> "User not authorized! Invalid token"

            //404
            code().toLong() == AUTH_NO_DATA -> "No find data! 404"

            //400 invalid data request
            code().toLong() == BAD_REQUEST_CODE -> "Incorrect  request!"

            //unknown:
            else -> "Unknown server problem!"
        }
    }

    //check internet connection
    is IOException -> {

        "No network exception!"
    }

    else -> "Unknown error!"

}
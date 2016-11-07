package com.moka.framework.extenstion

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.moka.mokatoyapp.server.FailureResponse

fun String.parseFailure(): FailureResponse? {
    val result: FailureResponse?

    result = try {
        Gson().fromJson(this, FailureResponse::class.java)
    } catch(err: JsonSyntaxException) {
        null
    }
    return result
}

fun <T> String.parse(className: Class<T>): T? {
    val result: T?

    result = try {
        Gson().fromJson(this, className)
    } catch(err: JsonSyntaxException) {
        null
    }
    return result
}
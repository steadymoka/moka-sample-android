package com.moka.framework.extenstion

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.moka.mokatoyapp.server.FailureResponse

fun String.parseFailure(): FailureResponse? {
    val result: FailureResponse?

    try {
        result = Gson().fromJson(this, FailureResponse::class.java)
    } catch(err: JsonSyntaxException) {
        result = null
    }
    return result
}

fun <T> String.parse(className: Class<T>): T? {
    val result: T?

    try {
        result = Gson().fromJson(this, className)
    } catch(err: JsonSyntaxException) {
        result = null
    }
    return result
}
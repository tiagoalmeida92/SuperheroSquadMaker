package com.tiago.superheroes_api

interface ApiConfigProvider {
    fun getBaseUrl(): String
    fun getApiKey(): String
    fun getReferer(): String
}

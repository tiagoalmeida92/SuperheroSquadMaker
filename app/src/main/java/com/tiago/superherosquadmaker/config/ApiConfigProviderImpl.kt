package com.tiago.superherosquadmaker.config

import com.tiago.superheroes_api.ApiConfigProvider
import com.tiago.superherosquadmaker.BuildConfig

class ApiConfigProviderImpl: ApiConfigProvider {
    override fun getBaseUrl(): String {
        return BuildConfig.MARVEL_SERVICE_BASE_URL
    }

    override fun getApiKey(): String {
        return BuildConfig.MARVEL_SERVICE_API_KEY
    }

    override fun getReferer(): String {
        return BuildConfig.MARVEL_SERVICE_REFERER
    }
}
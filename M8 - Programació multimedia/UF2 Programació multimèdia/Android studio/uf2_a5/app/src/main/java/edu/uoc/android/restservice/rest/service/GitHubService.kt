package edu.uoc.android.restservice.rest.service

import edu.uoc.android.restservice.rest.contants.ApiConstants
import edu.uoc.android.restservice.rest.model.Owner
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET(ApiConstants.GITHUB_USER_ENDPOINT)
    fun getOwner(@Path("owner") owner: String?): Call<Owner?>?
}

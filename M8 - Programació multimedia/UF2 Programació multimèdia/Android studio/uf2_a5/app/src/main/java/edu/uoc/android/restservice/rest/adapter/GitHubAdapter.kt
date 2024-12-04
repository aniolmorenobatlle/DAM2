package edu.uoc.android.restservice.rest.adapter

import edu.uoc.android.restservice.rest.contants.ApiConstants
import edu.uoc.android.restservice.rest.model.Owner
import edu.uoc.android.restservice.rest.service.GitHubService
import retrofit2.Call
import retrofit2.http.Path

class GitHubAdapter : BaseAdapter(ApiConstants.BASE_GITHUB_URL),
    GitHubService {
    private val gitHubService =
        createService(GitHubService::class.java)

    override fun getOwner(@Path(value = "owner") owner: String?): Call<Owner?>? {
        return gitHubService.getOwner(owner)
    }
}

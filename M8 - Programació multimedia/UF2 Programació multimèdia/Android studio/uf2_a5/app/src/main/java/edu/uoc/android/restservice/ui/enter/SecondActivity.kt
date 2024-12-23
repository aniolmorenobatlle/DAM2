package edu.uoc.android.restservice.ui.enter

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.uoc.android.restservice.R
import edu.uoc.android.restservice.rest.adapter.GitHubAdapter
import edu.uoc.android.restservice.rest.adapter.UserListAdapter
import edu.uoc.android.restservice.rest.classes.UserListClass
import edu.uoc.android.restservice.rest.model.Owner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserListAdapter
    private lateinit var userList: MutableList<UserListClass>

    private lateinit var avatarImageView: ImageView
    private lateinit var repositoriesTextView: TextView
    private lateinit var followingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        avatarImageView = findViewById(R.id.imageMainUser)
        repositoriesTextView = findViewById(R.id.repositories)
        followingTextView = findViewById(R.id.following)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val userInput = intent.getStringExtra("userInput")

        val username = userInput.toString()
        fetchUserData(username)
        fetchFollowersData(username)
    }

    private fun fetchUserData(username: String) {
        val gitHubAdapter = GitHubAdapter()
        val call = gitHubAdapter.getOwner(username)

        call?.enqueue(object : Callback<Owner?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Owner?>, response: Response<Owner?>) {
                if (response.isSuccessful) {
                    val owner = response.body()

                    owner?.let {
                        repositoriesTextView.text = "Repositories: ${it.publicRepos ?: 0}"
                        followingTextView.text = "Following: ${it.following ?: 0}"

                        Glide.with(this@SecondActivity)
                            .load(it.avatarUrl)
                            .circleCrop()
                            .into(avatarImageView)
                    }
                } else {
                    Toast.makeText(this@SecondActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Owner?>, t: Throwable) {
                Toast.makeText(this@SecondActivity, "Network Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchFollowersData(username: String) {
        val gitHubAdapter = GitHubAdapter()
        val call = gitHubAdapter.getFollowers(username)

        call.enqueue(object : Callback<List<Owner>?> {
            override fun onResponse(call: Call<List<Owner>?>, response: Response<List<Owner>?>) {
                if (response.isSuccessful) {
                    val followers = response.body()

                    followers?.let {
                        userList = mutableListOf()

                        it.forEach { follower ->
                            userList.add(
                                UserListClass(follower.login ?: "Unknown User", follower.avatarUrl ?: "")
                            )
                        }

                        userAdapter = UserListAdapter(userList)
                        recyclerView.adapter = userAdapter
                    }
                } else {
                    Toast.makeText(this@SecondActivity, "Error fetching followers", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Owner>?>, t: Throwable) {
                Toast.makeText(this@SecondActivity, "Network Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


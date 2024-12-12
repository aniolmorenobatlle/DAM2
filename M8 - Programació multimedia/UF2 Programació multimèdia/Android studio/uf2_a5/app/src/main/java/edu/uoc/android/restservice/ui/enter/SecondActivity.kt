package edu.uoc.android.restservice.ui.enter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.android.restservice.R
import edu.uoc.android.restservice.rest.adapter.UserListAdapter
import edu.uoc.android.restservice.rest.classes.UserList

class SecondActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserListAdapter
    private lateinit var userList: MutableList<UserList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userList = mutableListOf(
            UserList("John Doe", R.drawable.user),
            UserList("Jane Smith", R.drawable.building)
        )

        userAdapter = UserListAdapter(userList)
        recyclerView.adapter = userAdapter
    }
}
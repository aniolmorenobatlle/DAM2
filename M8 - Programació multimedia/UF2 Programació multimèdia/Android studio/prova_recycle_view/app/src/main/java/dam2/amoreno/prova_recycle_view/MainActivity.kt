package dam2.amoreno.prova_recycle_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dam2.amoreno.prova_recycle_view.adapter.UserListAdapter
import dam2.amoreno.prova_recycle_view.classes.UserListClass

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserListAdapter
    private lateinit var userList: MutableList<UserListClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userList = mutableListOf(
            UserListClass("John Doe", R.drawable.ic_launcher_foreground),
            UserListClass("Jane Smith", R.drawable.ic_launcher_foreground)
        )

        userAdapter = UserListAdapter(userList)
        recyclerView.adapter = userAdapter
    }
}
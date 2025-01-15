package dam2.amoreno.uf2_a7

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dam2.amoreno.uf2_a7.adapter.PostsListAdapter
import dam2.amoreno.uf2_a7.classes.PostsListClass

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postsAdapter: PostsListAdapter
    private lateinit var postsList: MutableList<PostsListClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        postsList = mutableListOf(
            PostsListClass(
                R.drawable.person1,
                "ajuntamentdepalamos",
                R.drawable.palamos,
                "ajuntamentdepalamos El poblat ibèric de Castell torna a estar obert al públic després de més...",
                1,
                2,
                3,
                "10 de febrer 2005"
            ),
            PostsListClass(
                R.drawable.person2,
                "marta_gomez",
                R.drawable.pals,
                "marta_gomez Quina energia més positiva, que bonica sortida!\n",
                4,
                5,
                6,
                "25 d'abril 2002"
            ),
            PostsListClass(
                R.drawable.person3,
                "joanfernandez",
                R.drawable.estartit,
                "joanfernandez Quin estil! M'agrada el detall en aquesta foto",
                7,
                8,
                9,
                "14 d'octubre 2001"
            ),
            PostsListClass(
                R.drawable.person3,
                "ana_ramirez",
                R.drawable.girona,
                "ana_ramirez Preciosa imatge, cada vegada que la miro em transmet tranquil·litat.",
                7,
                8,
                9,
                "11 de març de 2004"
            )
        )

        postsAdapter = PostsListAdapter(postsList)
        recyclerView.adapter = postsAdapter

    }
}
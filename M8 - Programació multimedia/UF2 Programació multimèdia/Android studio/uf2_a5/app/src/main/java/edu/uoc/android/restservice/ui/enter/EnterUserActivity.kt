package edu.uoc.android.restservice.ui.enter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import edu.uoc.android.restservice.R

class EnterUserActivity : AppCompatActivity(), View.OnClickListener {
    private var etUser: EditText? = null
    private lateinit var btnFollowers: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_user)

        etUser = findViewById(R.id.enter_user_edit_text)
        btnFollowers = findViewById(R.id.enter_user_button)

        btnFollowers.setOnClickListener(this)
    }

    private fun initViews() {

    }

    override fun onClick(v: View) {
        if (v === btnFollowers) {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}

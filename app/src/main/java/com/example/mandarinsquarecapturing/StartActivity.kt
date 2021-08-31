package com.example.mandarinsquarecapturing

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartActivity: AppCompatActivity() {
    lateinit var txtManchine: TextView
    lateinit var txtFriend: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        txtFriend = findViewById(R.id.txtFriend)
        txtManchine = findViewById(R.id.txtMachine)
        val intent = Intent(this, MainActivity::class.java)
        txtFriend.setOnClickListener {
            intent.putExtra("mode", 2)
            startActivity(intent)
        }
        txtManchine.setOnClickListener {
            intent.putExtra("mode", 1)
            startActivity(intent)
        }
    }
}
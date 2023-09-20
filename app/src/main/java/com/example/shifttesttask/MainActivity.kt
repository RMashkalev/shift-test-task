package com.example.shifttesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shifttesttask.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        supportFragmentManager.beginTransaction()
            .replace(mainBinding.mainFrame.id, Registration.newInstance())
            .commit()
    }
}
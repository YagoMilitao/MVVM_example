package com.example.mvvm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener(this)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setObserver()

        //binding.textWelcome.text = "Hello World!"
    }


    override fun onClick(v: View) {
        if(v.id == R.id.button_login){
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            viewModel.doLogin(email, password)
        }
    }

    private fun setObserver(){
        viewModel.welcome().observe(this,Observer {
            binding.textWelcome.text = it
        })
        viewModel.login().observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


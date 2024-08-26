package com.example.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private var textWelcome = MutableLiveData<String>()
    private var login = MutableLiveData<Boolean>()
    private val personRepository = PersonRepository()

    init {
        textWelcome.value = "Olá Mundo!"
    }

    fun welcome(): LiveData<String>{
        return textWelcome
    }

    fun login(): LiveData<Boolean>{
        return login
    }

    fun doLogin( email:String, password:String){
        //LOGICA DE VALIDACAO DE EMAIL E SENHA 
        login.value = personRepository.login(email, password)
    }
}
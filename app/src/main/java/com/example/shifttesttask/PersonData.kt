package com.example.shifttesttask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PersonRegistrationData {
    val name: MutableLiveData<String>
    val surname: MutableLiveData<String>
    val password: MutableLiveData<String>
    val repeatPassword: MutableLiveData<String>
    val allChecksPassed: MutableLiveData<Boolean>
}

class PersonData : ViewModel(), PersonRegistrationData {
    override val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    override val surname: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    override val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    override val repeatPassword: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    override val allChecksPassed: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}

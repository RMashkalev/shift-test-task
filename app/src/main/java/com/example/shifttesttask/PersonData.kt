package com.example.shifttesttask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface PersonRegistrationData {
    val name: MutableLiveData<String>
    val surname: MutableLiveData<String>
    val day: MutableLiveData<String>
    val month: MutableLiveData<String>
    val year: MutableLiveData<String>
    val birthdayCheck: MutableLiveData<Boolean>
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
    override val day: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    override val month: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    override val year: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    override val birthdayCheck: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
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

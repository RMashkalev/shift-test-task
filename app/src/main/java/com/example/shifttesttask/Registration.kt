package com.example.shifttesttask

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.shifttesttask.databinding.FragmentRegistrationBinding

class Registration : Fragment() {
    private lateinit var registrationBinding: FragmentRegistrationBinding
    private val personData: PersonData by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registrationBinding = FragmentRegistrationBinding.inflate(layoutInflater)
        return registrationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fieldCheck(registrationBinding.inputName, "name",2, 75)
        fieldCheck(registrationBinding.inputSurname, "surname", 3, 75)
        fieldCheck(registrationBinding.inputBirthday, "birthday",10, 10)
        fieldCheck(registrationBinding.inputFirstPassword, "password",8, 100)
        fieldCheck(registrationBinding.inputSecondPassword, "repeatPassword",8, 100)

        registrationBinding.registrationButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, MainScreen.newInstance())
                ?.commit()
        }
    }

    private fun fieldCheck(editText: EditText, target: String, min: Int, max: Int) {
        lateinit var value: String
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(result: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(result: Editable?) {
                value = if (result.toString().length in min..max) {
                    result.toString()
                } else {
                    ""
                }
                when(target) {
                    "name" -> personData.name.value = value
                    "surname" -> personData.surname.value = value
                    "birthday" -> personData.birthday.value = value
                    "password" -> personData.password.value = value
                    "repeatPassword" -> personData.password.value = value
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Registration()
    }
}
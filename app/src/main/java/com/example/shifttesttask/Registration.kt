package com.example.shifttesttask

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.shifttesttask.R.drawable.negative_edit_text_theme
import com.example.shifttesttask.R.drawable.positive_edit_text_theme
import com.example.shifttesttask.databinding.FragmentRegistrationBinding

class Registration : Fragment() {
    private lateinit var registrationBinding: FragmentRegistrationBinding
    val personData: PersonData by activityViewModels()
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
        initPersonData()
        inputFields()
        birthdaySelect()
        registrationButton()
    }


    private fun initPersonData() {
        personData.name.value = ""
        personData.surname.value = ""
        personData.day.value = ""
        personData.month.value = ""
        personData.year.value = ""
        personData.password.value = ""
        personData.repeatPassword.value = ""
    }

    private fun inputFields() {
        registrationBinding.inputName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(result: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(result: Editable?) {
                if (result.toString().length in 2..75) {
                    personData.name.value = result.toString()
                    registrationBinding.inputName.setBackgroundResource(positive_edit_text_theme)
                    registrationBinding.nameErrorMessage.text = ""
                } else {
                    personData.name.value = ""
                    registrationBinding.inputName.setBackgroundResource(negative_edit_text_theme)
                    registrationBinding.nameErrorMessage.text =
                        resources.getString(R.string.negative_name)
                }
                fieldsCheck()
            }
        })

        registrationBinding.inputSurname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(result: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(result: Editable?) {
                if (result.toString().length in 3..75) {
                    personData.surname.value = result.toString()
                    registrationBinding.inputSurname.setBackgroundResource(positive_edit_text_theme)
                    registrationBinding.surnameErrorMessage.text = ""
                } else {
                    personData.surname.value = ""
                    registrationBinding.inputSurname.setBackgroundResource(negative_edit_text_theme)
                    registrationBinding.surnameErrorMessage.text =
                        resources.getString(R.string.negative_surname)
                }
                fieldsCheck()
            }
        })

        registrationBinding.inputFirstPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(result: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(result: Editable?) {
                if (result.toString().length in 8..100) {
                    personData.password.value = result.toString()
                    registrationBinding.inputFirstPassword
                        .setBackgroundResource(positive_edit_text_theme)
                    registrationBinding.firstPasswordErrorMessage.text = ""
                } else {
                    personData.password.value = ""
                    registrationBinding.inputFirstPassword
                        .setBackgroundResource(negative_edit_text_theme)
                    registrationBinding.firstPasswordErrorMessage.text =
                        resources.getString(R.string.negative_first_password)
                }
                fieldsCheck()
            }
        })

        registrationBinding.inputSecondPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(result: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(result: Editable?) {
                if (result.toString() == personData.password.value.toString()) {
                    personData.repeatPassword.value = result.toString()
                    registrationBinding.inputSecondPassword
                        .setBackgroundResource(positive_edit_text_theme)
                    registrationBinding.secondPasswordErrorMessage.text = ""
                } else {
                    personData.repeatPassword.value = ""
                    registrationBinding.inputSecondPassword
                        .setBackgroundResource(negative_edit_text_theme)
                    registrationBinding.secondPasswordErrorMessage.text =
                        resources.getString(R.string.negative_second_password)
                }
                fieldsCheck()
            }
        })

    }

    fun fieldsCheck() {
        personData.allChecksPassed.value = (personData.name.value != ""
                && personData.surname.value != ""
                && personData.day.value != ""
                && personData.month.value != ""
                && personData.year.value != ""
                && personData.password.value != ""
                && personData.repeatPassword.value != "")
    }

    private fun birthdaySelect() {
        val days = mutableListOf<String>()
        val months = mutableListOf<String>()
        val years = mutableListOf<String>()
        for (i in 1..31) days.add(i.toString())
        for (i in 1..12) months.add(i.toString())
        for (i in 1950..2023) years.add(i.toString())
        val daysAdapter = activity?.let {
            ArrayAdapter<String>(it,
                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,
                days)
        }
        val monthsAdapter = activity?.let {
            ArrayAdapter<String>(it,
                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,
                months)
        }
        val yearsAdapter = activity?.let {
            ArrayAdapter<String>(it,
                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,
                years)
        }
        registrationBinding.day.adapter = daysAdapter
        registrationBinding.month.adapter = monthsAdapter
        registrationBinding.year.adapter = yearsAdapter

        registrationBinding.day.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                registrationBinding.day.setBackgroundResource(positive_edit_text_theme)
                personData.day.value = days[pos]
                fieldsCheck()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                registrationBinding.day.setBackgroundResource(negative_edit_text_theme)
            }

        })
        registrationBinding.month.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                registrationBinding.month.setBackgroundResource(positive_edit_text_theme)
                personData.month.value = months[pos]
                fieldsCheck()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                registrationBinding.month.setBackgroundResource(negative_edit_text_theme)
            }

        })
        registrationBinding.year.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                registrationBinding.year.setBackgroundResource(positive_edit_text_theme)
                personData.year.value = years[pos]
                fieldsCheck()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                registrationBinding.year.setBackgroundResource(negative_edit_text_theme)
            }

        })

        birthdayCheck()
    }

    private fun birthdayCheck() {

    }

    private fun registrationButton() {
        registrationBinding.registrationButton.setOnClickListener {
            if (personData.allChecksPassed.value == true) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.mainFrame, MainScreen.newInstance())
                    ?.commit()
            } else {
                Toast.makeText(
                    activity,
                    "Enter the correct registration data",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Registration()
    }
}
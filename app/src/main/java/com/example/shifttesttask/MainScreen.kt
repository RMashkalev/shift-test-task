package com.example.shifttesttask

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.shifttesttask.databinding.FragmentMainScreenBinding

class MainScreen : Fragment() {
    private lateinit var mainScreenBinding: FragmentMainScreenBinding
    private val personData: PersonData by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainScreenBinding = FragmentMainScreenBinding.inflate(layoutInflater)
        return mainScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        greetingButton()
        exitButton()
        saveData(personData.name.value.toString(), personData.surname.value.toString(),
            personData.day.value.toString(), personData.month.value.toString(),
            personData.year.value.toString(), personData.password.value.toString(),
            personData.repeatPassword.value.toString(),
            personData.allChecksPassed.value == true)
    }

    private fun greetingButton() {
        mainScreenBinding.greetingButton.setOnClickListener {
            val dialog = AlertDialog.Builder(activity)
            val title = R.string.greetingButton
            val message = getString(R.string.greetingMessage)
            dialog.setTitle(title)
            dialog.setMessage(message + " "
                    + personData.name.value + " "
                    + personData.surname.value)
            dialog.show()
        }
    }

    private fun exitButton() {
        mainScreenBinding.exitButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, Registration.newInstance())
                ?.commit()
            saveData()
        }
    }

    private fun saveData(name: String = "", surname: String = "", day: String = "",
                         month: String = "", year: String = "", password: String = "",
                         repeatPassword: String = "", allChecks: Boolean = false) {
        super.onDestroy()
        val sharedPreferences = activity
            ?.getSharedPreferences(R.string.shared_preferences.toString(), Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString(R.string.user_name.toString(), name)
        editor?.putString(R.string.user_surname.toString(), surname)
        editor?.putString(R.string.user_day.toString(), day)
        editor?.putString(R.string.user_month.toString(), month)
        editor?.putString(R.string.user_year.toString(), year)
        editor?.putString(R.string.user_password.toString(), password)
        editor?.putString(R.string.user_repeat_password.toString(), repeatPassword)
        editor?.putBoolean(R.string.user_all_checks.toString(),
            allChecks
        )
        editor?.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainScreen()
    }
}
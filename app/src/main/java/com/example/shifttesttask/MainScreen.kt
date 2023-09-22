package com.example.shifttesttask

import android.app.AlertDialog
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

        mainScreenBinding.exitButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, Registration.newInstance())
                ?.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainScreen()
    }
}
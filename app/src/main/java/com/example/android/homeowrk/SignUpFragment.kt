package com.example.android.homeowrk

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import com.example.android.homeowrk.data.User
import com.example.android.homeowrk.databinding.FragmentSignUpBinding
import com.example.android.homeowrk.viewmodels.*
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null

    private val binding get() = _binding!!

    private val vm: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val id: TextInputEditText = binding.idTextInputEdit
        id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                vm.setTextFiled(SignUpFormTextFiled.ID, p0)
            }
        })

        val password: TextInputEditText = binding.passwordTextInputEdit
        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                vm.setTextFiled(SignUpFormTextFiled.PASSWORD, p0)
            }
        })

        val email: TextInputEditText = binding.emailTextInputEdit
        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                vm.setTextFiled(SignUpFormTextFiled.EMAIL, p0)
            }
        })

        val idValidateButton: Button = binding.idValidateButton
        idValidateButton.setOnClickListener {
            vm.checkUserIsExists(vm.id.value ?: "")
        }

        val sexRadioGroup: RadioGroup = binding.sexRadioGroup
        sexRadioGroup.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.sex_radio_group_man -> vm.setSexFiled(SignUpFormSexFiled.MAN)
                R.id.sex_radio_group_girl -> vm.setSexFiled(SignUpFormSexFiled.WOMAN)
            }
        }

        val signUpButton: Button = binding.singUpButton
        signUpButton.setOnClickListener {
            if (vm.canSaveUser()) {
                val userId = vm.id.value ?: throw IllegalStateException("id cannot be null")
                val userPassword =
                    vm.password.value ?: throw IllegalStateException("password cannot be null")
                val userEmail =
                    vm.email.value ?: throw IllegalStateException("email cannot be null")
                val userSex = when (vm.sex.value) {
                    SignUpFormSexFiled.MAN -> "man"
                    SignUpFormSexFiled.WOMAN -> "woman"
                    else -> {
                        throw IllegalStateException("sex cannot be null")
                    }
                }

                val user =
                    User(userId, userPassword, userEmail, userSex)
                vm.createUser(user)

                // createUser 다음 실행을 보장하는지 확인 필요
                (activity as MainActivity).navigateToFlowerList()
            } else {
                (activity as MainActivity).showDialog(getString(R.string.alert_dialog_should_fill_form))
            }
        }

        vm.idValidationDialogVisibility.observe(viewLifecycleOwner) {
            if (it == SignUpFormDialogVisibility.SHOW) {
                val messageResource = when (vm.idValidationState.value) {
                    SignUpFormValidationState.NONE -> R.string.alert_dialog_id_validation_empty
                    SignUpFormValidationState.INVALID -> R.string.alert_dialog_id_validation_already_exists
                    SignUpFormValidationState.VALID -> R.string.alert_dialog_id_validation_can_use
                    else -> {
                        throw IllegalStateException("idValidationState cannot be null")
                    }
                }

                (activity as MainActivity).showDialog(getString(messageResource))
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
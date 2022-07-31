package com.example.android.homeowrk.viewmodels

import android.text.Editable
import android.util.Patterns
import androidx.lifecycle.*
import com.example.android.homeowrk.data.User
import com.example.android.homeowrk.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class SignUpFormTextFiled {
    ID, PASSWORD, EMAIL
}

enum class SignUpFormSexFiled {
    MAN, WOMAN, NONE
}

enum class SignUpFormDialogVisibility {
    HIDDEN, SHOW
}

enum class SignUpFormValidationState {
    NONE, INVALID, VALID
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _idValidationState = MutableLiveData<SignUpFormValidationState>().apply {
        value = SignUpFormValidationState.NONE
    }

    private val _idValidationDialogVisibility =
        MutableLiveData<SignUpFormDialogVisibility>().apply {
            value = SignUpFormDialogVisibility.HIDDEN
        }

    private val _password = MutableLiveData<String>().apply {
        value = ""
    }

    private val _passwordValidationState = MutableLiveData<SignUpFormValidationState>().apply {
        value = SignUpFormValidationState.NONE
    }

    private val _email = MutableLiveData<String>()

    private val _emailValidationState = MutableLiveData<SignUpFormValidationState>().apply {
        value = SignUpFormValidationState.NONE
    }

    private val _sex = MutableLiveData<SignUpFormSexFiled>().apply {
        value = SignUpFormSexFiled.NONE
    }

    private val _sexValidationState = MutableLiveData<SignUpFormValidationState>().apply {
        value = SignUpFormValidationState.NONE
    }

    fun canSaveUser(): Boolean = _idValidationState.value === SignUpFormValidationState.VALID
            && _emailValidationState.value === SignUpFormValidationState.VALID
            && _passwordValidationState.value === SignUpFormValidationState.VALID
            && _sexValidationState.value === SignUpFormValidationState.VALID


    fun checkUserIsExists(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (id.isNullOrBlank()) {
                _idValidationState.postValue(SignUpFormValidationState.NONE)
            } else {
                val state =
                    if (userRepository.exist(id)) SignUpFormValidationState.INVALID else SignUpFormValidationState.VALID
                _idValidationState.postValue(state)
            }
            _idValidationDialogVisibility.postValue(SignUpFormDialogVisibility.SHOW)
        }
    }

    private fun checkEmailValidation(email: String?): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email ?: "").matches()

    private fun checkPasswordValidation(password: String?): Boolean = !password.isNullOrBlank()

    fun createUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUser(user)
        }
    }

    fun setTextFiled(filed: SignUpFormTextFiled, value: Editable?) {
        val text: String = value?.toString() ?: ""

        when (filed) {
            SignUpFormTextFiled.EMAIL -> {
                _email.value = text
                when {
                    text.isBlank() -> _emailValidationState.value = SignUpFormValidationState.NONE
                    checkEmailValidation(text) -> _emailValidationState.value =
                        SignUpFormValidationState.VALID
                    else -> _emailValidationState.value = SignUpFormValidationState.INVALID
                }
            }
            SignUpFormTextFiled.ID -> {
                _id.value = text
                _idValidationState.value = SignUpFormValidationState.NONE
            }
            SignUpFormTextFiled.PASSWORD -> {
                _password.value
                when {
                    text.isBlank() -> _passwordValidationState.value =
                        SignUpFormValidationState.NONE
                    checkPasswordValidation(text) -> _passwordValidationState.value =
                        SignUpFormValidationState.VALID
                    else -> _passwordValidationState.value = SignUpFormValidationState.INVALID
                }
            }
        }
    }

    fun setSexFiled(sex: SignUpFormSexFiled) {
        _sex.value = sex
        _sexValidationState.value = SignUpFormValidationState.VALID
    }

    val id: LiveData<String> = _id
    val idValidationState: LiveData<SignUpFormValidationState> = _idValidationState
    val idValidationDialogVisibility: LiveData<SignUpFormDialogVisibility> =
        _idValidationDialogVisibility
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val sex: LiveData<SignUpFormSexFiled> = _sex
}
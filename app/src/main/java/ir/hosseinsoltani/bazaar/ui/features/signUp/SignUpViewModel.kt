package ir.hosseinsoltani.bazaar.ui.features.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hosseinsoltani.bazaar.model.repository.user.UserRepository
import ir.hosseinsoltani.bazaar.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class SignUpViewModel(private val loginRepository: UserRepository) : ViewModel() {

    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

    fun signUpUser( LoggingEvent : (String) -> Unit  ){
        viewModelScope.launch (coroutineExceptionHandler){
          val result =  loginRepository.signUp(name.value!!, email.value!!, password.value!!)
            LoggingEvent(result)
        }
    }
}
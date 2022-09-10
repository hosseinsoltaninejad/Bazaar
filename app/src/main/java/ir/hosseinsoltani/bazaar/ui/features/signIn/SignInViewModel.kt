package ir.hosseinsoltani.bazaar.ui.features.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hosseinsoltani.bazaar.model.repository.user.UserRepository
import ir.hosseinsoltani.bazaar.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class SignInViewModel(private val loginRepository: UserRepository) : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    fun signInUser(LoggingEvent : (String) -> Unit){
        viewModelScope.launch (coroutineExceptionHandler) {
            val result = loginRepository.signIn(email.value!! ,password.value!!)
            LoggingEvent(result)
        }
    }
}
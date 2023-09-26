package Register

import Database.RegisterRepository
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
//import com.example.mydatabaseapp.database.RegisterRepository
import java.lang.IllegalArgumentException

class RegisterViewModelFactory(
    private  val repository: RegisterRepository,
    private val application: Application):ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
       if(modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
           return RegisterViewModel(repository, application) as T
       }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
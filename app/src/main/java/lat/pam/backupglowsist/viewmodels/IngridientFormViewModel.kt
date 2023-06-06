package lat.pam.backupglowsist.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lat.pam.backupglowsist.data.model.InputUserData
import lat.pam.backupglowsist.services.Api

class IngridientFormViewModel (private val api: Api) : ViewModel() {

    fun sendDataToServer(input: List<String>) {
        viewModelScope.launch {
            for (inputData in input ) {
                val inputDataUser = InputUserData(response = inputData)
                val response = api.sendUserData(inputDataUser)

                if (response.isExecuted) {
                    Log.d("Success", "Berhasil mengirim data ke server")
                } else {
                    Log.d("Failed", "Gagal mengirim data ke server")
                }
            }
        }
    }
}
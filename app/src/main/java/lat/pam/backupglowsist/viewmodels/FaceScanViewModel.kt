package lat.pam.backupglowsist.viewmodels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FaceScanViewModel: ViewModel() {

    val photoUri: MutableLiveData<Uri> = MutableLiveData()

    fun setPhotoUri(uri: Uri) {
        photoUri.value = uri
    }
}
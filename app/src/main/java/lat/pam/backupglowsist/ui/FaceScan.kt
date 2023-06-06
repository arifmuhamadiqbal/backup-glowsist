package lat.pam.backupglowsist.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import lat.pam.backupglowsist.databinding.ActivityFaceScanBinding
import lat.pam.backupglowsist.viewmodels.FaceScanViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FaceScan : AppCompatActivity() {

    private lateinit var binding : ActivityFaceScanBinding
    private val FaceScanViewModel: FaceScanViewModel by viewModels()

    private var imageCapture : ImageCapture? = null
    private var cameraSelector : CameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: Exception) {
                Toast.makeText(
                    this@FaceScan,
                    "Failed to load camera",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityFaceScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startCamera()
        binding.btnCapture.setOnClickListener { takePhoto() }
        binding.btnFlip.setOnClickListener { switchCamera() }
        binding.btnCapture.setOnClickListener{ takePhoto() }

        FaceScanViewModel.photoUri.observe(this, { uri ->
            uri?.let {
                val intent = Intent(this@FaceScan, FaceScanResult::class.java)
                intent.putExtra("photoUri", it)
                startActivity(intent)
            }
        })

    }

    private fun createImageFile(context: Context): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val imageFile = createImageFile(this)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(imageFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        this@FaceScan,
                        "Failure",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(imageFile)
                    FaceScanViewModel.setPhotoUri(savedUri)
                }
            }
        )
    }

    private fun switchCamera() {
        cameraSelector =
            if (cameraSelector == (CameraSelector.DEFAULT_FRONT_CAMERA)) CameraSelector.DEFAULT_BACK_CAMERA
            else CameraSelector.DEFAULT_FRONT_CAMERA

        startCamera()
    }

}
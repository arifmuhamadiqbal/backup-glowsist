package lat.pam.backupglowsist.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import lat.pam.backupglowsist.R
import lat.pam.backupglowsist.databinding.ActivityFaceScanResultBinding
import java.io.File

class FaceScanResult : AppCompatActivity() {

    private lateinit var binding: ActivityFaceScanResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityFaceScanResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val photoUri = intent.getParcelableExtra<Uri>("photoUri")
        binding.ivImage.setImageURI(photoUri)

        binding.scanAgain.setOnClickListener {
            val intent = Intent(this@FaceScanResult, FaceScan::class.java)
            startActivity(intent)
        }

        binding.backHome.setOnClickListener {
            val intent = Intent(this@FaceScanResult, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
package lat.pam.backupglowsist.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import lat.pam.backupglowsist.R

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val btnToScan : ImageView = findViewById(R.id.scan_feature)
        btnToScan.setOnClickListener {
            val intent = Intent(this, ScanSuggestions::class.java)
            startActivity(intent)
        }

        val btnToForm : ImageView = findViewById(R.id.ingredients_feature)
        btnToForm.setOnClickListener {
            val intent = Intent(this, UserIngredientsForm::class.java)
            startActivity(intent)
        }
    }
}
package lat.pam.backupglowsist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lat.pam.backupglowsist.R

class IngredientsFormResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_ingredients_form_result)
    }
}
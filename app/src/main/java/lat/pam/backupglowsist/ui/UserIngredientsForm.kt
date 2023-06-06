package lat.pam.backupglowsist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import lat.pam.backupglowsist.databinding.ActivityUserIngredientsFormBinding
import lat.pam.backupglowsist.viewmodels.IngridientFormViewModel

class UserIngredientsForm : AppCompatActivity() {

    private lateinit var binding: ActivityUserIngredientsFormBinding
    private val UserIngredientsFormViewModel: IngridientFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityUserIngredientsFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnSubmit: Button = binding.btnSubmit

        btnSubmit.setOnClickListener{ sendDataToServer() }

    }

    private fun sendDataToServer() {
        val input = mutableListOf<String>()

        if (binding.rbAcneYes.isChecked) {
            input.add("Yes")
        } else if (binding.rbAcneNo.isChecked) {
            input.add("No")
        } else {
            input.add("null")
        }

        if (binding.rbRednessYes.isChecked) {
            input.add("Yes")
        } else if (binding.rbRednessNo.isChecked) {
            input.add("No")
        } else {
            input.add("null")
        }

        if (binding.rbNormal.isChecked) {
            input.add("Normal")
        } else if (binding.rbOily.isChecked) {
            input.add("Oily")
        } else if (binding.rbDry.isChecked){
            input.add("Dry")
        } else {
            input.add("null")
        }

        if (binding.rbTdkSensitive.isChecked) {
            input.add("Normal")
        } else if (binding.rbCkpSensitive.isChecked) {
            input.add("Sensitive")
        } else if (binding.rbSgtSensitive.isChecked){
            input.add("Very Sensitive")
        } else {
            input.add("null")
        }

        UserIngredientsFormViewModel.sendDataToServer(input)

    }
}
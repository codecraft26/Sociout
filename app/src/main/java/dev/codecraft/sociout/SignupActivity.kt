package dev.codecraft.sociout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import dev.codecraft.sociout.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signupbtn.setOnClickListener{
            signInLauncher.launch(signInIntent)
        }

    }
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }
    private val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
        .build()
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {

            val intent= Intent(this@SignupActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {

            if (response != null) {

                Toast.makeText(this,
                    "A sign in error occurred! Please try again later!",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }
}
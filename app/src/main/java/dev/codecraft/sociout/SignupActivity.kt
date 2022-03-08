package dev.codecraft.sociout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.codecraft.sociout.daos.UsersDao

import dev.codecraft.sociout.databinding.ActivitySignupBinding
import dev.codecraft.sociout.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signupbtn.setOnClickListener {
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
       val currentuser=auth.currentUser
        if(currentuser!=null){
            val user = Users(currentuser.uid, currentuser.displayName,currentuser.photoUrl.toString())
            val usersDao = UsersDao()
            usersDao.addUser(user)
        }



        if (result.resultCode == RESULT_OK) {

            val intent = Intent(this@SignupActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {

            if (response != null) {

                Toast.makeText(
                    this,
                    "A sign in error occurred! Please try again later!",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

}
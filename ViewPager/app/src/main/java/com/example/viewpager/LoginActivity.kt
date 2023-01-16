package com.example.viewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Api
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var  mGoogleSigninClient : GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()

        //Auto Login Google
        if (GoogleSignIn.getLastSignedInAccount(this)!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    //Auto Login Firebase
        if (firebaseAuth.currentUser !=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("201616624596-7vsfrk5bsq9ao9vi23jtunhe2399uum9.apps.googleusercontent.com")
            .requestEmail()
            .build()

        mGoogleSigninClient = GoogleSignIn.getClient(this,gso)
        firebaseAuth = FirebaseAuth.getInstance()

        btnGoogle.setOnClickListener(View.OnClickListener {
            val signInIntent: Intent = mGoogleSigninClient.signInIntent
            startActivityForResult(signInIntent,100)
        })

        btnLoginCust.setOnClickListener(View.OnClickListener {
            firebaseAuth.signInWithEmailAndPassword(txtEmail.text.toString(),txtPassword.text.toString()).addOnCompleteListener {
                task -> if (task.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        })

        txtRegister.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RegistrasiUser::class.java))
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100){
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account : GoogleSignInAccount = task.getResult(ApiException::class.java)
                Log.d("Debug", account.toString())
                if (account != null) {
                    auth_token(account)
                }
            } catch (e:ApiException){
                Log.e("Exception API", e.message.toString())
            }
        }
    }

    fun auth_token(account: GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener{task ->
            if (task.isSuccessful){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }


}
package com.example.mysafer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.mysafer.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.util.jar.Manifest as Manifest

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    var googleSignInClient : GoogleSignInClient?=null
    var GoogleLoginCode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("580434499005-9rr3ggiveklbgili6m5uoscnkt89k0gm.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)

        binding.loginBtn.setOnClickListener{
            var i = googleSignInClient?.signInIntent
            startActivityForResult(i,GoogleLoginCode)
        }

        //카메라권한과 비디오 권한 요청
        ActivityCompat.requestPermissions(this, arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        ),0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==GoogleLoginCode){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account!!.idToken,null)

            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        saveUserDataToDatabase(task.result!!.user)
                    }
                }
        }
    }

    //구글 로그인한 계정을 firebase 데베에 넣어주기
    fun saveUserDataToDatabase(user : FirebaseUser?){
        val email = user?.email
        val uid = user?.uid
        var userDTO = UserDTO()
        userDTO.email = email

        FirebaseFirestore.getInstance().collection("users").document(uid!!).set(userDTO)
        finish()
        startActivity(Intent(this,MainActivity::class.java))
    }
}
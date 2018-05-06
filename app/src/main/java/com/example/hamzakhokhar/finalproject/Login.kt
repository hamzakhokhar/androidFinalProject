package com.example.hamzakhokhar.finalproject

/**
 * Created by hamzakhokhar on 4/30/18.
 */
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login: AppCompatActivity(){

    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginProgress: ProgressDialog
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        mAuth = FirebaseAuth.getInstance()

        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.new_user_button)
        loginEmail = findViewById(R.id.username_text)
        loginPassword = findViewById(R.id.password_text)



        registerButton.setOnClickListener {
            val intent = Intent(applicationContext, Registration::class.java)
            startActivity(intent)
            finish()
        }

        loginButton.setOnClickListener {
            val name = loginEmail.text.toString().trim()
            val pwd = loginPassword.text.toString().trim()

            if(TextUtils.isEmpty(name)) {
                loginEmail.error = "Enter email."
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(pwd)) {
                loginPassword.error = "Enter password."
                return@setOnClickListener
            }

            login(name, pwd)
        }
    }

    private fun login(name: String, pwd: String) {
        // loginProgress.setMessage("Please wait...")
        // loginProgress.show()

        mAuth.signInWithEmailAndPassword(name, pwd).addOnCompleteListener(this) {task ->
            if(task.isSuccessful) {
                //       loginProgress.setMessage("Login successful!")
                //       loginProgress.dismiss()
                Toast.makeText(this, "Authentication successful!", Toast.LENGTH_SHORT).show()

                val intent = Intent(applicationContext, MapsActivity::class.java)
                startActivity(intent)
                finish()
            }

            else {
                Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }

            // loginProgress.dismiss()
        }

    }

}
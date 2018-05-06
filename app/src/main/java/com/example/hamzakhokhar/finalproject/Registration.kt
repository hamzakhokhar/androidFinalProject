package com.example.hamzakhokhar.finalproject

/**
 * Created by hamzakhokhar on 4/30/18.
 */
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registration: AppCompatActivity(){

    private lateinit var saveButton: Button
    private lateinit var returnButton: Button
    private lateinit var loginEmailR: EditText
    private lateinit var loginPasswordR: EditText
   // private lateinit var loginProgress2: ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDb: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        mAuth = FirebaseAuth.getInstance()
        mDb = FirebaseDatabase.getInstance().getReference("Users")

        saveButton = findViewById(R.id.save_button)
        returnButton = findViewById(R.id.return_login)
        loginEmailR = findViewById(R.id.username_register)
        loginPasswordR = findViewById(R.id.password_register)

        returnButton.setOnClickListener{
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }

        saveButton.setOnClickListener{
            val name = loginEmailR.text.toString().trim()
            val pwd = loginPasswordR.text.toString().trim()

            if(TextUtils.isEmpty(name)) {
                loginEmailR.error = "Enter email."
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(pwd)) {
                loginPasswordR.error = "Enter password."
                return@setOnClickListener
            }
            else {
                createUser(name, pwd) }
        }
    }


    private fun createUser(name: String, pwd: String) {

        // loginProgress2.setMessage("Please wait...")
        // loginProgress2.show()

        mAuth.createUserWithEmailAndPassword(name, pwd).addOnCompleteListener(this) {task ->
            if (task.isSuccessful) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val uid = currentUser!!.uid

                val userMap = HashMap<String, String>()
                userMap["name"] = name

                mDb = FirebaseDatabase.getInstance().getReference("Users").child(uid)
                mDb.setValue(userMap).addOnCompleteListener(OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Registration sucessful!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                        finish()
                    }
                })
            }

            else {
                Toast.makeText(this, "Registration failed.", Toast.LENGTH_SHORT).show()
            }

            // loginProgress2.dismiss()
        }
    }

}
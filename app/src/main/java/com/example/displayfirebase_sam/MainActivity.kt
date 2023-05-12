package com.example.displayfirebase_sam

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var edtname: EditText
    lateinit var edtemail:EditText
    lateinit var btnsave:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtname=findViewById(R.id.edtName)
        edtemail=findViewById(R.id.edtEmail)
        btnsave=findViewById(R.id.btnSave)

        btnsave.setOnClickListener {

            var name = edtname.text.toString().trim()
            var email= edtemail.text.toString().trim()

            var time_id = System.currentTimeMillis().toString().trim()

            //progress bar
            var progress = ProgressDialog(this)
            progress.setTitle("Submitting data")
            progress.setMessage("Please wait")


            //validate

            if (name.isEmpty()||email.isEmpty()){
                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            }else{

                var my_child = FirebaseDatabase.getInstance().reference.child("Names"+ time_id)
                var user_data = User(name, email, time_id)


                //show progress
                progress.show()

                my_child.setValue(user_data).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this, "Data Uploaded", Toast.LENGTH_SHORT).show()

                        var gotoview=Intent(this, View_users::class.java)
                        startActivity(gotoview)



                    }else{


                        Toast.makeText(this, "Failed to upload data", Toast.LENGTH_SHORT).show()

                    }
                }

            }

        }

    }
}
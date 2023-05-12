package com.example.displayfirebase_sam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class View_users : AppCompatActivity() {

    lateinit var my_list_view:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_users)

        my_list_view=findViewById(R.id.mListpeople)

        var users:ArrayList<User> = ArrayList()

        var myadapter =CustomAdapter(applicationContext,users)

        var my_db = FirebaseDatabase.getInstance().reference.child("Names")

        my_db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                //get data and display in array
                users.clear()
                for (snap in snapshot.children){
                    var person = snap.getValue(User::class.java)
                    users.add(person!!)
                }

                myadapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Failed to Display Data", Toast.LENGTH_SHORT).show()

            }

        })

    }
}
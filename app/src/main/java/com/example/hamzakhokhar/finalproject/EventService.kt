package com.example.hamzakhokhar.finalproject

import com.google.firebase.database.FirebaseDatabase



/**
 * Created by hamzakhokhar on 4/28/18.
 */

class EventService {
    companion object {
        fun save(event: EventActivity){
            // Write a message to the database
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Activities")
            val key = myRef.push().key
            event.id = key
            myRef.child(key).setValue(event)


        }

        fun get(){

        }

        fun getAll(){

        }

        fun delete(event: EventActivity){
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Activities")
            myRef.child(event.id).removeValue()
        }
    }
}
package com.example.hamzakhokhar.finalproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.AdapterView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_list.*


/**
 * Created by hamzakhokhar on 4/25/18.
 */
class ActivityList: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val database = FirebaseDatabase.getInstance()
        val db_activities = database.getReference("Activities")
        val _context = this;

        db_activities.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("Count " ,""+snapshot.getChildrenCount());
                val events = snapshot.children.map {
                    it.getValue(EventActivity::class.java)
                }
                event_activity_list_view.adapter = EventActivityAdapter(_context, events as ArrayList<EventActivity>)
            }
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        event_activity_list_view.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
        }
    }
}
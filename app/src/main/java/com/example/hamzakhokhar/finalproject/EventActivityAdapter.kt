package com.example.hamzakhokhar.finalproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mypopsy.maps.StaticMap

/**
 * Created by hamzakhokhar on 4/25/18.
 */
class EventActivityAdapter(context: Context, eventList: ArrayList<EventActivity>): BaseAdapter() {

    private var eventsList: ArrayList<EventActivity> = eventList
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListViewHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.activity_list_card, parent, false)
            vh = ListViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListViewHolder
        }

        val event: EventActivity = eventsList[position]
        val _address = StringBuilder()
                .append(event.address)
                .append(" ")
                .append(event.city)
                .append(" ")
                .append(event.state)
                .append(" ")
                .append(event.zip)
                .toString()

        vh.editTextName.text = eventsList[position].name;
        vh.editTextType.text = eventsList[position].type;
        vh.editTextStartTime.text = eventsList[position].starttime
        vh.editTextEndTime.text = eventsList[position].endtime
        vh.editTextStartDate.text = eventsList[position].startdate
        vh.editTextEndDate.text = eventsList[position].enddate
        vh.editTextAddress.text = _address
        vh.button_delete.setOnClickListener {
            EventService.delete(eventsList[position])
        }

        val mapImage: StaticMap = StaticMap()
                .center(_address)
                .size(400, 200)
                .marker(_address)

        Glide.with(view!!).load(mapImage.toURL()).into(vh.mapImageView)

        return view
    }

    override fun getItem(position: Int): Any {
        return eventsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return eventsList.size
    }

}

private class ListViewHolder(view: View?){
    val editTextName: TextView = view?.findViewById(R.id.edittext_name) as TextView
    val editTextType: TextView = view?.findViewById(R.id.edittext_type) as TextView
    val editTextStartDate: TextView = view?.findViewById(R.id.edittext_startdate) as TextView
    val editTextStartTime: TextView = view?.findViewById(R.id.edittext_starttime) as TextView
    val editTextEndDate: TextView = view?.findViewById(R.id.edittext_enddate) as TextView
    val editTextEndTime: TextView = view?.findViewById(R.id.edittext_endtime) as TextView
    val editTextAddress : TextView = view?.findViewById(R.id.edittext_address) as TextView
    val mapImageView: ImageView = view?.findViewById(R.id.mapview) as ImageView
    val button_delete: Button = view?.findViewById(R.id.button_delete) as Button

}

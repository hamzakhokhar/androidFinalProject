package com.example.hamzakhokhar.finalproject

/**
 * Created by hamzakhokhar on 4/25/18.
 */

class EventActivity {
    var id: String? = null
    var name: String? = null
    var type: String? = null
    var starttime: String? = null
    var endtime: String? = null
    var startdate: String? = null
    var enddate: String? = null
    var address: String? = null
    var state: String? = null
    var city: String? = null
    var zip: String? = null
    constructor(
            id: String,
            name: String,
            type: String,
            starttime: String,
            endtime: String,
            startdate: String,
            enddate: String,
            address: String,
            state: String,
            city: String,
            zip: String)
    {
        this.id = id
        this.name = name
        this.type = type
        this.startdate = startdate
        this.enddate = enddate
        this.starttime = starttime
        this.endtime = endtime
        this.address = address
        this.state = state
        this.city = city
        this.state = state
        this.zip = zip

    }

    constructor()
}
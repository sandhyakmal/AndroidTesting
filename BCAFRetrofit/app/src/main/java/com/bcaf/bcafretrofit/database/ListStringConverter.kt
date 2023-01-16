package com.bcaf.bcafretrofit.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListStringConverter {

    @TypeConverter
    fun fromList(value: String): List<String>{
        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toList(list: List<String>): String{
        val gson = Gson()
        return gson.toJson(list)
    }
}
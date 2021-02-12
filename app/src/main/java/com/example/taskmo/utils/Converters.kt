package com.example.taskmo.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

object Converters {
    /*
    @TypeConverter
    @JvmStatic
    fun jsonToListCity(value: String?) =
        Gson().fromJson(value, Array<ResponseCity.DataItem>::class.java).toCollection(ArrayList())

    @TypeConverter
    @JvmStatic
    fun listToJsonCity(value: List<ResponseCity.DataItem>?): String {
        return if (value == null)
            ""
        else
            Gson().toJson(value)
    }
    */

    @TypeConverter
    @JvmStatic
    fun jsonToListEducation(value: String?) =
        Gson().fromJson(value, Array<String>::class.java).toCollection(ArrayList())

    @TypeConverter
    @JvmStatic
    fun listToJsonEducation(value: List<String>?): String {
        return if (value == null)
            ""
        else
            Gson().toJson(value)
    }
}
package com.example.taskmo.domain.repository

import androidx.room.PrimaryKey
import com.example.taskmo.utils.Const.BOOLEAN_DEFAULT_VALUE
import com.example.taskmo.utils.Const.INT_DEFAULT_VALUE
import com.example.taskmo.utils.Const.STRING_DEFAULT_VALUE
import com.google.gson.annotations.SerializedName

class Comments {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @SerializedName("postId")
    var postId: Int = INT_DEFAULT_VALUE

    @SerializedName("name")
    var name: String = STRING_DEFAULT_VALUE

    @SerializedName("email")
    var email: String = STRING_DEFAULT_VALUE

    @SerializedName("body")
    var body: String = STRING_DEFAULT_VALUE

}
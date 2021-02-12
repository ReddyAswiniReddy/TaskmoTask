package com.example.taskmo.data.account.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskmo.utils.Const.BOOLEAN_DEFAULT_VALUE
import com.example.taskmo.utils.Const.INT_DEFAULT_VALUE
import com.example.taskmo.utils.Const.STRING_DEFAULT_VALUE
import com.google.gson.annotations.SerializedName

@Entity
class UserFavoritePosts {

    @PrimaryKey(autoGenerate = true)
    var id:Int=0

    @SerializedName("userId")
    var userId: Int = INT_DEFAULT_VALUE

    @SerializedName("title")
    var title: String = STRING_DEFAULT_VALUE

    @SerializedName("body")
    var body: String = STRING_DEFAULT_VALUE

    var uploadedOnServer:Boolean = true


}
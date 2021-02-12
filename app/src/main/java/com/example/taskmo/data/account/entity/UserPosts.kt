package com.example.taskmo.data.account.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskmo.utils.Const.INT_DEFAULT_VALUE
import com.example.taskmo.utils.Const.STRING_DEFAULT_VALUE
import com.google.gson.annotations.SerializedName

@Entity
class UserPosts(): Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id:Int=0

    @SerializedName("userId")
    var userId: Int = INT_DEFAULT_VALUE

    @SerializedName("title")
    var title: String = STRING_DEFAULT_VALUE

    @SerializedName("body")
    var body: String = STRING_DEFAULT_VALUE

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        userId = parcel.readInt()
        title = parcel.readString().toString()
        body = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(userId)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<UserPosts> {
        override fun createFromParcel(parcel: Parcel): UserPosts {
            return UserPosts(parcel)
        }

        override fun newArray(size: Int): Array<UserPosts?> {
            return arrayOfNulls(size)
        }
    }
}
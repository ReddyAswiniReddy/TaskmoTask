package com.example.taskmo.data.account

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskmo.data.account.entity.UserPosts

@Dao
interface AccountDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserPosts(user: List<UserPosts>)

    @Query("SELECT * FROM UserPosts")
    fun getUserPosts(): List<UserPosts>


    @Query("Select * from userposts")
    fun getUserPostLiveData(): LiveData<List<UserPosts>?>

    @Query("DELETE FROM userposts")
    fun clearTable()

}

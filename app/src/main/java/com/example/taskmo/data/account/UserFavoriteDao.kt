package com.example.taskmo.data.account

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskmo.data.account.entity.UserFavoritePosts
import com.example.taskmo.data.account.entity.UserPosts

@Dao
interface UserFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserFavPosts(user: List<UserFavoritePosts>)

    @Insert
    fun addUserFavPost(user: UserFavoritePosts)

    @Query("DELETE FROM UserFavoritePosts WHERE id =:delId")
    fun removeUserFavPost(delId:Int)

    @Query("SELECT * FROM UserFavoritePosts")
    fun getUserFavPosts(): List<UserFavoritePosts>


    @Query("Select * from userfavoriteposts")
    fun getUserFavPostLiveData(): LiveData<List<UserFavoritePosts>?>

    @Query("UPDATE userfavoriteposts SET uploadedOnServer=:value WHERE id = :postId")
    fun updateUploadedOnServer(postId:Int,value:Boolean)


    @Query("DELETE FROM userfavoriteposts")
    fun clearTable()
}

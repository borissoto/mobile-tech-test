package com.borissoto.mobiletest.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Post(
    @PrimaryKey()
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int,
    val favorite: Boolean,
): Parcelable
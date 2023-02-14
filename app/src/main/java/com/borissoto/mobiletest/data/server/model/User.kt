package com.borissoto.mobiletest.data.server.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//class User : ArrayList<UserItem>()
@Parcelize
data class UserItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
): Parcelable

@Parcelize
data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
): Parcelable

@Parcelize
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
): Parcelable

@Parcelize
data class Geo(
    val lat: String,
    val lng: String
): Parcelable
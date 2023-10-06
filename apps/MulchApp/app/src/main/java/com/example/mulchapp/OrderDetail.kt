package com.example.mulchapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderDetail(
    val mulchType: String,
    val totalPrice:Double,
    val numberOfCubicYards:Int,
    val pricePerCubicYard:Double,
    val deliveryCharge: Double,
    val salesTax:Double,
    val state:String,
    val zipCode:String,
    val street:String,
    val city:String,
    val email:String,
    val phone:String,
    val mulchCost:Double
) : Parcelable





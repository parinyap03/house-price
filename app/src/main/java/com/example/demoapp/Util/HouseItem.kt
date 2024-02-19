package com.example.demoapp.Util

//ตรงกับdatabase เอาไว้ส่งค่าไปที่คลาสอื่น
data class HouseItem (val image_url: String = "", val location: String = "", val price: String = "", val long: Double = Double.NaN, val lat: Double = Double.NaN)
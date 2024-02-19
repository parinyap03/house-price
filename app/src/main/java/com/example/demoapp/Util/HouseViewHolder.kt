package com.example.demoapp.Util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.R

class HouseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//รายการที่สามารถเปลี่ยนแปลงได้
    private val imageView = view.findViewById<ImageView>(R.id.ivHouse)
    private val textViewHouseDetail = view.findViewById<TextView>(R.id.tvHouseDetail)
    private val textViewPrice = view.findViewById<TextView>(R.id.tvPriceTag)
    private val context = view.context
    private val imageViewActionButton = view.findViewById<ImageView>(R.id.ivActionBtn)
//เอาไว้แก้สิ่งที่อยากแก้หรือโชว์
    fun bind(houseItem: HouseItem) {
//       imageView.setImageResource(houseItem.imageId)
        Glide.with(context).load(houseItem.image_url).into(imageView);
        textViewHouseDetail.text = houseItem.location
        textViewPrice.text = houseItem.price
        imageViewActionButton.setOnClickListener {
        var clickedData = GlobalBox.savedHouseListItem.first {
            it.location == houseItem.location
        }
        GlobalBox.selectedHouseItem = clickedData //เก็บตัวแปรที่แมชกันไว้ในนี้
        GlobalBox.savedBottomNavigation?.setItemSelected(R.id.mapPage) //สั่งเปลี่ยนเมนูไปที่หน้าmapPage เมื่อกดปุ่ม
    }
    }
}
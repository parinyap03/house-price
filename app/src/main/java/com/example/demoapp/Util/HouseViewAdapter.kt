package com.example.demoapp.Util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
//รับitemเข้ามา ตัวที่รับเข้ามา,HouseViewHolder,house_template สามารถเปลี่ยนได้
class HouseViewAdapter (private val houseItemList: List<HouseItem>) : RecyclerView.Adapter<HouseViewHolder>() {
//   เป็นตัวระบุว่าจะให้เทมเพลตไหน ซึ่งในนี้ระบุว่าใช้ house_template
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.house_template, parent, false)
        return HouseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return houseItemList.size
    }

    override fun onBindViewHolder(viewHolder: HouseViewHolder, currentPage: Int) {
        val viewItem = houseItemList[currentPage]
        viewHolder.bind(viewItem)
    }
}
package com.task.noteapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url.isNullOrEmpty().not()) {
        Glide.with(view.context).load(url).into(view)
    } else {
        view.isVisible = false
    }
}

@BindingAdapter("longToDispDate")
fun setDate(view: TextView, date: Long?) {
    date?.let {
        val temp = Date()
        temp.time = date
        val dateStr = SimpleDateFormat("dd/mm/yyyy", Locale("tr")).format(temp)
        view.text = dateStr
    }
}
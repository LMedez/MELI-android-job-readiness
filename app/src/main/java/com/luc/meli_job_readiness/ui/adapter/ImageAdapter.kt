package com.luc.meli_job_readiness.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.getSystemService
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.luc.meli_job_readiness.R
import com.luc.meli_job_readiness.databinding.ImageItemBinding
import com.luc.meli_job_readiness.databinding.ProductItemBinding

class ImageAdapter(private val imageList: List<String>) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun getCount() = imageList.size

    override fun isViewFromObject(view: View, object1: Any) = view == object1

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = container.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.image_item,container, false)
        val binding = ImageItemBinding.bind(view)
        binding.imageUrl = imageList[position]
        container.addView(view)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, object1: Any) {
        return container.removeView(object1 as ConstraintLayout)
    }
}
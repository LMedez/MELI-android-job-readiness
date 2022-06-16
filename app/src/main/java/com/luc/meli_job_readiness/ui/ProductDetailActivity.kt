package com.luc.meli_job_readiness.ui

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.databinding.ActivityProductDetailBinding
import com.luc.meli_job_readiness.ui.adapter.ImageAdapter

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            enterTransition = transitionAnimation(true)
            returnTransition = transitionAnimation(false)
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val product = intent.getSerializableExtra("product") as DataModel.Product

        with(binding) {

            productPriceTV.text = "$ ${product.price}"
            productTitleTV.text = product.title
            sellerQuantityTV.text = if (product.condition == "new")
                "Nuevo | ${product.soldQuantity}" else "Usado | ${product.soldQuantity}"

            imageCountCH.text = "1 / ${product.pictures.size}"
            imagesVP.adapter = ImageAdapter(product.pictures.map { it.secureUrl })

            imagesVP.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    binding.imageCountCH.text = "${position + 1} / ${product.pictures.size}"
                }
            })
        }

    }

    private fun transitionAnimation(forward: Boolean): MaterialSharedAxis {
        return MaterialSharedAxis(MaterialSharedAxis.X, forward).apply {
            addTarget(binding.root.id)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
    }
}
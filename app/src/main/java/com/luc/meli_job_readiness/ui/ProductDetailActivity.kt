package com.luc.meli_job_readiness.ui

import android.os.Bundle
import android.view.Menu
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.luc.meli_job_readiness.R
import com.luc.meli_job_readiness.data.model.DataModel
import com.luc.meli_job_readiness.databinding.ActivityProductDetailBinding
import com.luc.meli_job_readiness.ui.adapter.ImageAdapter
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProductDetailBinding.inflate(layoutInflater)

        // Set up transition animations for this activity
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            enterTransition = transitionAnimation(true)
            returnTransition = transitionAnimation(false)
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Add support for the Toolbar for this activity
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Get the product in the extras from the MainActivity intent
        val product = intent.getSerializableExtra(MainActivity.PRODUCT) as DataModel.Product

        // Set up the data for views
        with(binding) {
            productPriceTV.text = "$ ${product.price.toCurrencyPrice()}"
            productTitleTV.text = product.title
            //descriptionTV.text = product.description
            sellerQuantityTV.text =
                if (product.condition == "new") "${getString(R.string.neww)} | ${product.soldQuantity.toInt()} ${getString(R.string.sell)}"
                else "${getString(R.string.used)} | ${product.soldQuantity.toInt()} ${getString(R.string.sell)}"

            imageCountCH.text = "1 / ${product.pictures.size}"
            imagesVP.adapter = ImageAdapter(product.pictures.map { it.secureUrl })

            // Change the text of the chip with the current position when user change the page in the image viewpager
            imagesVP.addOnPageChangeListener(onPageChangeListener(product.pictures.size))

        }
    }

    /**
     * Extension function that convert the price in ARS
     */
    private fun Double.toCurrencyPrice(): String {
        val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("es", "ES"))
        formatter.maximumFractionDigits = 0
        formatter.currency = Currency.getInstance("ARS")

        return formatter.format(this.roundToInt()).dropLast(3)
    }

    /**
     * Inflate the menu in the toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_detail_menu, menu)
        return true
    }

    /**
     * MaterialSharedAxis transition that is performed when the activity is open
     */
    private fun transitionAnimation(forward: Boolean): MaterialSharedAxis {
        return MaterialSharedAxis(MaterialSharedAxis.X, forward).apply {
            addTarget(binding.root.id)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
    }

    /**
     * Page Listener that changes the current position and count of images in the image viewpager
     */
    private fun onPageChangeListener(pictureSize: Int) = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        override fun onPageSelected(position: Int) { binding.imageCountCH.text = "${position + 1} / $pictureSize" }
        override fun onPageScrollStateChanged(state: Int) {}
    }
}
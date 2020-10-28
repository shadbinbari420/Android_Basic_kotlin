package com.example.taslk_7_retrofit.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.taslk_7_retrofit.R
import com.example.taslk_7_retrofit.core.BaseActivity
import com.example.taslk_7_retrofit.home.model.Food
import com.example.taslk_7_retrofit.home.model.FoodCallback
import com.example.taslk_7_retrofit.home.model.HomeModel
import com.example.taslk_7_retrofit.home.model.HomeModelImpl
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    override fun setLayoutId(): Int = R.layout.activity_main
    override fun setToolbar(): Toolbar {
        toolbar.title = getString(R.string.title_homepage)
        return toolbar
    }

    override val isHomeUpButtonEnable: Boolean get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showFoodDetails()
    }

    private fun showFoodDetails() {

        progressBar.visibility = View.VISIBLE

        val homeModel: HomeModel = HomeModelImpl()

        homeModel.getFoodDetails(object : FoodCallback {

            override fun onSuccess(food: Food) {
                progressBar.visibility = View.GONE
                materialCardView.visibility = View.VISIBLE

                Glide.with(iv_food)
                    .load(food.imageUrl)
                    .into(iv_food)
                tv_food_name.text = food.name
                tv_price_value.text = getString(R.string.price_format, food.price)

            }

            override fun onError(errorMessage: Throwable) {
                progressBar.visibility = View.GONE
                materialCardView.visibility = View.GONE
                showToast(errorMessage.localizedMessage)
                Logger.e(errorMessage.localizedMessage)
            }
        })
    }

}
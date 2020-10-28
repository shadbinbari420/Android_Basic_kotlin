package com.example.taslk_7_retrofit.home.model

import com.example.taslk_7_retrofit.network.FoodApiInterface
import com.example.taslk_7_retrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeModelImpl: HomeModel {

    private val apiInterface = RetrofitClient.getClient().create(FoodApiInterface::class.java)
    private val call = apiInterface.getFoodDetails()

    override fun getFoodDetails(foodCallback: FoodCallback) {

        call.enqueue(object : Callback<Food> {

            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                response.body()?.let {
                    foodCallback.onSuccess(it)
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                foodCallback.onError(t)
            }

        })
    }
}
package com.yourcompany.medicamentregister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.farmaceut.ba/"

class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    lateinit var myData: List<MyDataItem>
    lateinit var myAdditionalData: List<MyAdditionalDataItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_medicaments.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_medicaments.layoutManager = linearLayoutManager

        getMyAdditionalData()
        getMyData()

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {

                myData = response.body()!!

                var combinedList = combineData(myData,myAdditionalData)

                myAdapter = MyAdapter(baseContext, combinedList)
                myAdapter.notifyDataSetChanged()
                recyclerview_medicaments.adapter = myAdapter

                myAdapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {
                    override fun setOnClickListener(position: Int) {
                        val intent = Intent(baseContext, MedicamentDetailsActivity::class.java)
                        intent.putExtra("secondViewName", combinedList[position].name)
                        intent.putExtra("secondViewAtc", combinedList[position].atc)
                        intent.putExtra("categoryName", combinedList[position].myAdditionalData.name)
                        intent.putExtra("shortDescription", combinedList[position].shortDescription)
                        intent.putExtra("activeSupstanceValue",combinedList[position].activeSubstanceValue)
                        intent.putExtra("recommendedDailyDoseValue", combinedList[position].minimumDailyDose)
                        intent.putExtra("description", combinedList[position].description)
                        startActivity(intent)
                    }

                })
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }
        })
    }

    private fun getMyAdditionalData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitBuilder.getAdditionalData()

        retrofitData.enqueue(object : Callback<List<MyAdditionalDataItem>?> {
            override fun onResponse(
                call: Call<List<MyAdditionalDataItem>?>,
                response: Response<List<MyAdditionalDataItem>?>
            ) {
                myAdditionalData = response.body()!!
            }

            override fun onFailure(call: Call<List<MyAdditionalDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure" + t.message)
            }
        })
    }

    private fun combineData(myData: List<MyDataItem>, myAdditionalDate: List<MyAdditionalDataItem>) : ArrayList<MyDataItem> {
        var combinedList = arrayListOf<MyDataItem>()

        for (medicament in myData) {
            for (category in myAdditionalData) {
                if (medicament.categoryId == category.id) {
                    medicament.myAdditionalData = category
                    combinedList.add(medicament)
                }
            }
        }
        return combinedList
    }
}





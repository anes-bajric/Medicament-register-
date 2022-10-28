package com.yourcompany.medicamentregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.yourcompany.medicamentregister.databinding.ActivityMedicamentDetailsBinding

class MedicamentDetailsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMedicamentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicamentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val photoUrl = "https://cdn-icons-png.flaticon.com/512/883/883356.png"

        Glide.with(this)
            .load(photoUrl)
            .fitCenter()
            .into(binding.imageView)

        val secondViewName = getIntent().getStringExtra("secondViewName")
        val secondViewAtc = getIntent().getStringExtra("secondViewAtc")
        val categoryName = getIntent().getStringExtra("categoryName")
        val shortDescription = getIntent().getStringExtra("shortDescription")
        val activeSupstanceValue = getIntent().getIntExtra("activeSupstanceValue", 0)
        val recommendedDailyDoseValue = getIntent().getIntExtra("recommendedDailyDoseValue", 0)
        val description = getIntent().getStringExtra("description")

        binding.run {activeSupstanceTv.text = "Aktivna supstanca"
        activeSupstanceRatioTv.text = "Omjer aktivne supstance"
        recommendedDailyDoseTv.text = "Preporucena dnevna doza"
        activeSupstanceRatioValue.text = "/"}


        binding.secondViewName.text = secondViewName
        binding.secondViewAtc.text = secondViewAtc
        binding.categoryName.text = " - " + categoryName
        binding.shortDescription.text = shortDescription
        binding.activeSupstanceValue.text = activeSupstanceValue.toString()
        binding.recommendedDailyDoseValue.text = recommendedDailyDoseValue.toString()
        binding.description.text = description

    }
}
package com.yourcompany.medicamentregister

data class MyDataItem(
    val activeSubstanceMeasurementUnit: String,
    val activeSubstanceQuantityMeasurementUnit: String,
    val activeSubstanceSelectedQuantity: Int,
    val activeSubstanceValue: Int,
    val atc: String,
    val categoryId: Int,
    val description: String,
    val forbiddenInPregnancy: Boolean,
    val id: Int,
    val maximumDailyDose: Int,
    val minimumDailyDose: Int,
    val name: String,
    val shortDescription: String,
    val showOnCalculator: Boolean,

    var myAdditionalData: MyAdditionalDataItem,
)
package com.example.automotiveapp.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class BrandResponse(
    val data: List<Brand>
)

@Parcelize
data class Brand(
    val id: Int,
    val name: String,
    val image: String
) : Parcelable


data class ModelResponse(
    val data: List<Model>
)

data class Model(
    val id: Int,
    val vehicle_id: Int,
    val name: String,
    val image: String,
    val least_deposit: Int,
    val least_installment: Int,
    val attribute: String,
    val attribute_image: String,
    val identification_attribute_id : Int,
    val identification_attribute_value_id: Int,
    val attribute_value: String,
    val price: Int,
    val parent_brand: String,
    val parent_brand_image: String,
    val attributes_hint_list: List<AttributeHint>
)

data class AttributeHint(
    val attribute: String,
    val image: String,
    val value: String
)


data class VehicleResponse(
    val data: List<Vehicle>
)

@Parcelize
data class Vehicle(
    val id: Int,
    val has_seller: Boolean,
    val year: String,
    val additional_images: List<String>,
    val brand_id: Int,
    val brand: String,
    val brand_image: String,
    val model_id: Int,
    val model: String,
    val model_image: String,
    val name: String,
    val price: Int,
    val hidden_price: Int,
    val stock_available: Boolean,
    val generation: String,
    val selling_type: String?,
    val is_wishlisted: Boolean,
    val in_compare_list: Boolean,
    val extra_attributes: List<String>?
) : Parcelable

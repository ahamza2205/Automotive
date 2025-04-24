package com.example.automotiveapp.data.remote

data class BrandResponse(
    val data: List<Brand>
)

data class Brand(
    val id: Int,
    val name: String,
    val image: String
)


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

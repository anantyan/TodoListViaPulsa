package id.anantyan.todolistviapulsa.data.remote.model

import com.google.gson.annotations.SerializedName

data class RequestModel(
    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("person_name")
    val personName: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)

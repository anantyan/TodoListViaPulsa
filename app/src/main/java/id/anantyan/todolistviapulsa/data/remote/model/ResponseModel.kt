package id.anantyan.todolistviapulsa.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("person_name")
	val personName: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

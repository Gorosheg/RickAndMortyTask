package gorosheg.network.model

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
)
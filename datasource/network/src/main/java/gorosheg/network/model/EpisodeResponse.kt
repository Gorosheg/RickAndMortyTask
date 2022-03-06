package gorosheg.network.model

import com.google.gson.annotations.SerializedName

internal class Episode(
    @SerializedName("results")
    val results: List<EpisodeInfo>
)

internal class EpisodeInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
)
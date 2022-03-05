package gorosheg.network.model

import com.google.gson.annotations.SerializedName

class CharactersResponse(
    @SerializedName("results")
    val description: List<DescriptionResponse>
)
package gorosheg.network.model

import com.google.gson.annotations.SerializedName

class RickAndMortyResponse(
    @SerializedName("results")
    val info: List<CharacterInfo>,
)
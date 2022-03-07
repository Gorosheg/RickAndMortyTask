package gorosheg.myapplication.model

import java.io.Serializable

class Episode(
    override val name: String,
    val airDate: String,
) : Serializable, Item(name)
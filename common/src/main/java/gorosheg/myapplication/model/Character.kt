package gorosheg.myapplication.model

import java.io.Serializable

class Character(
    val id: Int,
    override val name: String,
    val image: String
) : Serializable, Item(name)
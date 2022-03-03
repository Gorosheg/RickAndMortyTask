package gorosheg.characters.presentation.recicler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gorosheg.characters.R
import gorosheg.characters.presentation.model.Character

class RecyclerViewHolder(
    charView: View,
    private val onCharClick: (Character) -> Unit
) : RecyclerView.ViewHolder(charView) {

    private var character: Character? = null
    private val characterName: TextView = charView.findViewById(R.id.name)

    init {
        charView.setOnClickListener {
            character?.let(onCharClick::invoke)
        }
    }

    fun bind(character: Character) {
        this.character = character
        characterName.text = character.name
    }
}
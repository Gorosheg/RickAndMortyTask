package gorosheg.characters.presentation.recicler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gorosheg.characters.R
import gorosheg.myapplication.Character

class RecyclerViewHolder(
    private val charView: View,
    private val onCharClick: (Character) -> Unit
) : RecyclerView.ViewHolder(charView) {

    private var character: Character? = null
    private val characterName: TextView = charView.findViewById(R.id.name)
    private val characterPhoto: ImageView = charView.findViewById(R.id.photo)

    init {
        charView.setOnClickListener {
            character?.let(onCharClick::invoke)
        }
    }

    fun bind(character: Character) {
        this.character = character
        characterName.text = character.name

        Glide // Добавление изображения из интернета
            .with(charView) // context
            .load(character.image) // Ссылка на изображение
            .into(characterPhoto) // View

        characterPhoto.clipToOutline = true
    }
}
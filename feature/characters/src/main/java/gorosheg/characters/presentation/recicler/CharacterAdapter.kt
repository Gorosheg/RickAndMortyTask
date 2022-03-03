package gorosheg.characters.presentation.recicler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gorosheg.characters.R
import gorosheg.characters.presentation.model.Character

internal class CharacterAdapter(
    private val onCharClick: (Character) -> Unit
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    var items: List<Character> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(CharactersDiffCallback(items, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_characters, parent, false)
        return RecyclerViewHolder(view, onCharClick)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
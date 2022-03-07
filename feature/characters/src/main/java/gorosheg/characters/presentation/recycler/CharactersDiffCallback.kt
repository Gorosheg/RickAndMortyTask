package gorosheg.characters.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import gorosheg.myapplication.model.Character

internal class CharactersDiffCallback(
    private val oldList: List<Character>,
    private val newList: List<Character>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCharacter = oldList[oldItemPosition]
        val newCharacter = newList[newItemPosition]
        return oldCharacter.id == newCharacter.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCharacter = oldList[oldItemPosition]
        val newCharacter = newList[newItemPosition]
        return oldCharacter == newCharacter
    }
}
package gorosheg.characters.presentation.recicler

import androidx.recyclerview.widget.DiffUtil
import gorosheg.myapplication.Character

class CharactersDiffCallback(
    private val oldList: List<Character>,
    private val newList: List<Character>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldChar = oldList[oldItemPosition]
        val newChar = newList[newItemPosition]
        return oldChar.id == newChar.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCity = oldList[oldItemPosition]
        val newCity = newList[newItemPosition]
        return oldCity == newCity
    }
}
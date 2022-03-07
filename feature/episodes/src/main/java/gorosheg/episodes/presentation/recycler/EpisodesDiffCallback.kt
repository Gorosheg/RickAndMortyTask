package gorosheg.episodes.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import gorosheg.myapplication.model.Episode

internal class EpisodesDiffCallback(
    private val oldList: List<Episode>,
    private val newList: List<Episode>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEpisode = oldList[oldItemPosition]
        val newEpisode = newList[newItemPosition]
        return oldEpisode.name == newEpisode.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEpisode = oldList[oldItemPosition]
        val newEpisode = newList[newItemPosition]
        return oldEpisode == newEpisode
    }
}
package gorosheg.episodes.presentation.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gorosheg.episodes.R
import gorosheg.myapplication.model.Episode
import gorosheg.myapplication.utils.ItemsDiffCallback
import gorosheg.myapplication.utils.inflate

internal class EpisodesAdapter : RecyclerView.Adapter<EpisodesRecyclerViewHolder>() {

    var items: List<Episode> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(ItemsDiffCallback(items, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesRecyclerViewHolder {
        val view: View = parent.inflate(R.layout.list_of_episodes)
        return EpisodesRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodesRecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
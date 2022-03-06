package gorosheg.episodes.presentation.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gorosheg.episodes.R
import gorosheg.myapplication.model.Episodes
import gorosheg.myapplication.utils.inflate

internal class EpisodesAdapter() : RecyclerView.Adapter<RecyclerViewHolder>() {

    var items: List<Episodes> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(EpisodesDiffCallback(items, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = parent.inflate(R.layout.list_of_episodes)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
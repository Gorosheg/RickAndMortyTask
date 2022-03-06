package gorosheg.episodes.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import gorosheg.episodes.R
import gorosheg.episodes.presentation.recycler.EpisodesAdapter
import gorosheg.myapplication.R.*
import gorosheg.myapplication.model.Episodes
import gorosheg.myapplication.utils.showToast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EpisodesFragment : Fragment(R.layout.fragment_episodes) {

    private val rootView by lazy { requireNotNull(view) }
    private val recyclerView: RecyclerView by lazy { rootView.findViewById(R.id.EpisodesList) }
    private val adapter: EpisodesAdapter by lazy { EpisodesAdapter() }

    private val episodesList: List<String> by lazy {
        arguments?.getStringArray(EPISODES_KEY)?.toList()!!
    }

    private val viewModel: EpisodesViewModel by viewModel { parametersOf(episodesList) }
    private val swipeRefresh: SwipeRefreshLayout by lazy { rootView.findViewById(R.id.charactersRefresh) }
    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
        swipeRefresh.setOnRefreshListener { getEpisodes() }
        getEpisodes()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun getEpisodes() {

        disposable += viewModel.getEpisodes()
            .subscribe { character -> adapter.items = character }

        disposable += viewModel.error
            .subscribe {
                it.printStackTrace()
                showToast(getString(string.unknown_error))
            }
    }

    companion object {

        private const val EPISODES_KEY = "EPISODES_KEY"

        fun newInstance(episodesList: List<String>) = EpisodesFragment().apply {
            arguments = Bundle().apply {
                putStringArray(EPISODES_KEY, episodesList.toTypedArray())
            }
        }
    }

}
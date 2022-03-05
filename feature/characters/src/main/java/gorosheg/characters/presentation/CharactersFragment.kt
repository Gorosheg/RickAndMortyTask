package gorosheg.characters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import gorosheg.characters.R
import gorosheg.characters.presentation.recicler.CharacterAdapter
import gorosheg.myapplication.CharacterNavigator
import gorosheg.myapplication.R.*
import gorosheg.myapplication.model.Character
import gorosheg.myapplication.showToast
import gorosheg.myapplication.model.NetworkExceptions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val rootView by lazy { requireNotNull(view) }
    private val recyclerView: RecyclerView by lazy { rootView.findViewById(R.id.CharacterList) }
    private val adapter: CharacterAdapter by lazy { CharacterAdapter(::navigateToDescriptionScreen) }
    private val viewModel: CharactersViewModel by viewModel()
    private val swipeRefresh: SwipeRefreshLayout by lazy { rootView.findViewById(R.id.charactersRefresh) }
    private val disposable = CompositeDisposable()
    private val navigator: CharacterNavigator by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        loadCharacters()
        swipeRefresh.setOnRefreshListener { loadCharacters() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun navigateToDescriptionScreen(character: Character) {
        activity?.let { navigator.navigateToDescriptionScreen(it, character.id) }
    }

    private fun loadCharacters() {
        loaderChange(true)
        disposable += viewModel.loadCharacters()
            .subscribe { character ->
                adapter.items = character
            }

        disposable += viewModel.error
            .subscribe(::makeToast)
        loaderChange(false)
    }

    private fun makeToast(throwable: NetworkExceptions) {
        when (throwable) {
            NetworkExceptions.NotFound -> {
                showToast(getString(string.info_not_found))
            }

            NetworkExceptions.Unknown -> {
                showToast(getString(string.unknown_error))
            }
        }
    }

    private fun loaderChange(it: Boolean) {
        swipeRefresh.isRefreshing = it
    }

    companion object {

        fun newInstance() = CharactersFragment()

    }
}
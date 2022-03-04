package gorosheg.characters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import gorosheg.characters.R
import gorosheg.characters.presentation.recicler.CharacterAdapter
import gorosheg.myapplication.Character
import gorosheg.myapplication.showToast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val rootView by lazy { requireNotNull(view) }
    private val recyclerView: RecyclerView by lazy { rootView.findViewById(R.id.CharacterList) }
    private val adapter: CharacterAdapter by lazy { CharacterAdapter(::navigateToDescriptionScreen) }
    private val viewModel: CharactersViewModel by viewModel()
    private val swipeRefresh: SwipeRefreshLayout by lazy { rootView.findViewById(R.id.charactersRefresh) }
    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        loadCharacters()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun navigateToDescriptionScreen(character: Character) {
        // navigate to next screen
    }

    private fun loadCharacters() {
        disposable += viewModel.loadCharacters()
            .subscribe { character ->
                adapter.items = character
            }

        disposable += viewModel.error
            .subscribe(::makeToast)
    }

    private fun makeToast(throwable: NetworkExceptions) {
        when (throwable) {
            NetworkExceptions.NotFound -> {
                showToast(R.string.character_not_found.toString())
            }

            NetworkExceptions.Unknown -> {
                showToast(R.string.unknown_error.toString())
            }
        }
    }

    companion object {

        fun newInstance() = CharactersFragment()

    }
}
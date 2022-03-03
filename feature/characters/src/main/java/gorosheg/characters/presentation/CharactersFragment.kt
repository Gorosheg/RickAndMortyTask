package gorosheg.characters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import gorosheg.characters.R
import gorosheg.characters.presentation.model.Character
import gorosheg.characters.presentation.recicler.CharacterAdapter


class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val rootView by lazy { requireNotNull(view) }
    private val recyclerView: RecyclerView by lazy { rootView.findViewById(R.id.CharacterList) }
    private val adapter: CharacterAdapter by lazy { CharacterAdapter(::navigateToDescriptionScreen) }
    private val swipeRefresh: SwipeRefreshLayout by lazy { rootView.findViewById(R.id.charactersRefresh) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

    }

    private fun navigateToDescriptionScreen(character: Character) {
        // navigate to next screen
    }

    companion object {

        fun newInstance() = CharactersFragment()

    }
}
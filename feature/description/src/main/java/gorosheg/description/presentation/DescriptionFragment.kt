package gorosheg.description.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import gorosheg.description.R
import gorosheg.myapplication.R.*
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import gorosheg.myapplication.navigator.DescriptionNavigator
import gorosheg.myapplication.utils.showToast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DescriptionFragment : Fragment(R.layout.fragment_description) {
    private val rootView by lazy { requireNotNull(view) }
    private val disposable = CompositeDisposable()
    private var episodes: List<String> = emptyList()
    private val navigator: DescriptionNavigator by inject()

    private val characterId: Int by lazy {
        arguments?.getInt(CHARACTER_KEY) as Int
    }

    private val viewModel: DescriptionViewModel by viewModel { parametersOf(characterId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDescription()

        val openEpisodes: Button = rootView.findViewById(R.id.openEpisodes)

        openEpisodes.setOnClickListener {
            navigateToEpisodesScreen(this.episodes)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun loadDescription() {
        disposable += viewModel.loadDescription()
            .doOnSuccess(::handleDescription)
            .subscribe()

        disposable += viewModel.loadLocation()
            .doOnSuccess(::handleLocation)
            .subscribe()

        disposable += viewModel.error
            .subscribe {
                it.printStackTrace()
                showToast(getString(string.unknown_error))
            }
    }

    private fun navigateToEpisodesScreen(episodes: List<String>) {
        navigator.navigateToEpisodesScreen(requireActivity(), episodes)
    }

    private fun handleDescription(description: Description) {
        val photo: ImageView = rootView.findViewById(R.id.photo)
        val name: TextView = rootView.findViewById(R.id.name)
        val species: TextView = rootView.findViewById(R.id.species)
        val status: TextView = rootView.findViewById(R.id.status)

        episodes = description.episodes

        Glide.with(rootView)
            .load(description.image)
            .into(photo)

        photo.clipToOutline = true
        name.text = description.name
        species.text = description.species
        status.text = description.status
    }

    @SuppressLint("SetTextI18n")
    private fun handleLocation(location: Location) {
        val locationView: TextView = rootView.findViewById(R.id.location)
        locationView.text = "${location.name}, ${location.dimension}"
    }

    companion object {

        private const val CHARACTER_KEY = "CHARACTER_KEY"

        fun newInstance(charId: Int) = DescriptionFragment().apply {
            arguments = Bundle().apply {
                putInt(CHARACTER_KEY, charId)
            }
        }
    }
}
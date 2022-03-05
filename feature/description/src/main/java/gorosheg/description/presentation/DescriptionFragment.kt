package gorosheg.description.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import gorosheg.description.R
import gorosheg.myapplication.R.*
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import gorosheg.myapplication.showToast
import gorosheg.myapplication.model.NetworkExceptions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import org.koin.androidx.viewmodel.ext.android.viewModel

class DescriptionFragment : Fragment(R.layout.fragment_description) {
    private val rootView by lazy { requireNotNull(view) }
    private val disposable = CompositeDisposable()
    private val viewModel: DescriptionViewModel by viewModel()

    private val characterId: Int by lazy {
        arguments?.getSerializable(CHARACTER_KEY) as Int
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDescription()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun loadDescription() {
        disposable += viewModel.loadDescription(characterId)
            .doOnSuccess {
                handleDescription(it)
            }
            .subscribe()

        disposable += viewModel.loadLocation(characterId)
            .doOnSuccess {
                handleLocation(it)
            }
            .subscribe()

        disposable += viewModel.error
            .subscribe(::makeToast)
    }

    private fun handleDescription(description: Description) {
        val photo: ImageView = rootView.findViewById(R.id.photo)
        val name: TextView = rootView.findViewById(R.id.name)
        val species: TextView = rootView.findViewById(R.id.species)
        val status: TextView = rootView.findViewById(R.id.status)


        Glide
            .with(rootView)
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

    companion object {

        private const val CHARACTER_KEY = "CHARACTER_KEY"

        fun newInstance(charId: Int) = DescriptionFragment().apply {
            arguments = Bundle().apply {
                putSerializable(CHARACTER_KEY, charId)
            }
        }

    }
}
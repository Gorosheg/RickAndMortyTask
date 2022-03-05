package gorosheg.myapplication

import androidx.fragment.app.FragmentActivity

interface DescriptionNavigator {

    fun navigateToEpisodesScreen(activity: FragmentActivity, characterId: Int)

}
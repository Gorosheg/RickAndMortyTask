package gorosheg.myapplication

import androidx.fragment.app.FragmentActivity

interface CharacterNavigator {

    fun navigateToDescriptionScreen(activity: FragmentActivity, characterId: Int)

}
package gorosheg.red_mad_robot_test_task

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import gorosheg.characters.presentation.CharactersFragment
import gorosheg.description.presentation.Description

private const val CHARACTERS = "CharacterScreen"
private const val DESCRIPTION = "Description"

class NavigatorImpl : MainNavigator {

    override fun navigateToCharactersScreen(activity: FragmentActivity) {
        val charactersFragment = CharactersFragment.newInstance()

        activity.supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentHolder, charactersFragment)
            .addToBackStack(CHARACTERS)
            .commit()
    }

    override fun navigateToDescriptionScreen(activity: FragmentActivity) {
        val fragment = Description.newInstance()
        activity.navigateToDescriptionFragment(fragment)
    }

    private fun FragmentActivity.navigateToDescriptionFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.fragmentHolder, fragment)
            addToBackStack(DESCRIPTION)
            commit()
        }
    }

    override fun back(activity: FragmentActivity) {
        activity.supportFragmentManager.popBackStack()
    }
}
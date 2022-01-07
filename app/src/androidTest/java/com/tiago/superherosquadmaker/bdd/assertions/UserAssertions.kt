package com.tiago.superherosquadmaker.bdd.assertions

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.tiago.superheroes_ui.list.SuperheroListActivity

class UserAssertions(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<SuperheroListActivity>, SuperheroListActivity>
) {

    fun onSuperoesListScreen() = SuperheroesListScreenAssertions(composeTestRule)
    fun onSuperheroDetailScreen() = SuperheroDetailScreenAssertions(composeTestRule)

}

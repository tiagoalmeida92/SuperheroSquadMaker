package com.tiago.superherosquadmaker.bdd.actions

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.tiago.superheroes_ui.list.SuperheroListActivity

class UserActions(private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<SuperheroListActivity>, SuperheroListActivity>) {

    fun onSuperheroesListScreen() = SuperHeroesListScreenActions(composeTestRule)
    fun onSuperheroDetailScreen() = SuperheroDetailScreenActions(composeTestRule)

}

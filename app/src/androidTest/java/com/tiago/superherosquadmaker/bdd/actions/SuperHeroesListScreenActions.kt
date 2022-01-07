package com.tiago.superherosquadmaker.bdd.actions

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.tiago.superheroes_ui.list.SuperheroListActivity
import com.tiago.superherosquadmaker.bdd.testdata.Superheroes
import java.lang.Thread.sleep

class SuperHeroesListScreenActions(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<SuperheroListActivity>, SuperheroListActivity>
) {

    fun navigatesToFirstHero() {
        composeTestRule
            .onNodeWithText(Superheroes.FirstSuperheroName)
            .performClick()
    }
}

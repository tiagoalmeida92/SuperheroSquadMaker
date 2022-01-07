package com.tiago.superherosquadmaker.bdd.assertions

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.tiago.superheroes_ui.list.SuperheroListActivity
import com.tiago.superherosquadmaker.R
import com.tiago.superherosquadmaker.bdd.testdata.Superheroes

class SuperheroesListScreenAssertions(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<SuperheroListActivity>, SuperheroListActivity>
) {

    fun seesFirstHero() {
        composeTestRule
            .onNodeWithText(Superheroes.FirstSuperheroName)
            .assertIsDisplayed()
    }

    fun seesSquadHeader() = apply {
        val headerLabel = composeTestRule.activity.getString(R.string.squad_header_label)
        composeTestRule
            .onNodeWithText(headerLabel)
            .assertIsDisplayed()
    }

    fun seesFirstHeroOnSquad() {
        composeTestRule
            .onAllNodesWithText(Superheroes.FirstSuperheroName)
            .assertCountEquals(2)
    }


}

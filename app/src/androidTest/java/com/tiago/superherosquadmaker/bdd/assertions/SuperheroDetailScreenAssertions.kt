package com.tiago.superherosquadmaker.bdd.assertions

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.tiago.superheroes_ui.list.SuperheroListActivity
import com.tiago.superherosquadmaker.R
import com.tiago.superherosquadmaker.bdd.testdata.Superheroes

class SuperheroDetailScreenAssertions(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<SuperheroListActivity>, SuperheroListActivity>
) {

    fun seesFirstHero(){
        composeTestRule.onNodeWithText(Superheroes.FirstSuperheroName).assertIsDisplayed()
        val noDescriptionAvailable = composeTestRule.activity.getString(R.string.no_description_label)
        composeTestRule.onNodeWithText(noDescriptionAvailable).assertIsDisplayed()
    }
}

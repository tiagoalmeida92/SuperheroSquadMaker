package com.tiago.superherosquadmaker.bdd

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.tiago.superheroes_ui.list.SuperheroListActivity
import com.tiago.superherosquadmaker.bdd.actions.UserActions

class When(composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<SuperheroListActivity>, SuperheroListActivity>) {

    val user = UserActions(composeTestRule)

}
package com.tiago.superherosquadmaker.bdd.actions

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.tiago.superheroes_ui.list.SuperheroListActivity
import com.tiago.superherosquadmaker.R

class SuperheroDetailScreenActions(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<SuperheroListActivity>, SuperheroListActivity>,
) {
    fun clicksHireAction() = apply {
        val hireButtonText = composeTestRule.activity.getString(R.string.hire_to_squad_action)

        composeTestRule.onNodeWithText(hireButtonText)
            .performClick()
    }

    fun clicksClose(){
        val closeDescription = composeTestRule.activity.getString(R.string.close_description)

        composeTestRule.onNodeWithContentDescription(closeDescription)
            .performClick()
    }


}

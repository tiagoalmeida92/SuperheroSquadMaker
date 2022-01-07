package com.tiago.superherosquadmaker

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.tiago.superheroes_ui.list.SuperheroListActivity
import com.tiago.superherosquadmaker.bdd.Given
import com.tiago.superherosquadmaker.bdd.Then
import com.tiago.superherosquadmaker.bdd.When
import com.tiago.superherosquadmaker.rules.MockServerRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SquadFlowTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<SuperheroListActivity>()

    @get:Rule
    val mockServerRule = MockServerRule()

    private val given = Given(mockServerRule)
    private val `when` = When(composeTestRule)
    private val then = Then(composeTestRule)

    @Test
    fun userSeesSuperheroesList() {
        given.apiReturnsSuperheroes()

        then.user.onSuperoesListScreen()
            .seesFirstHero()
    }

    @Test
    fun userSeesSuperheroesDetail() {
        given.apiReturnsSuperheroes()

        `when`.user.onSuperheroesListScreen()
            .navigatesToFirstHero()

        then.user.onSuperheroDetailScreen()
            .seesFirstHero()
    }

    @Test
    fun userAddsSuperheroToSquad_thenSquadListShows() {
        given.apiReturnsSuperheroes()

        `when`.user.onSuperheroesListScreen()
            .navigatesToFirstHero()

        `when`.user.onSuperheroDetailScreen()
            .clicksHireAction()
            .clicksClose()

        then.user.onSuperoesListScreen()
            .seesSquadHeader()
            .seesFirstHeroOnSquad()
    }

}

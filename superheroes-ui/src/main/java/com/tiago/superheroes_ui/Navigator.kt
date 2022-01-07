package com.tiago.superheroes_ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_ui.detail.SuperheroDetailActivity

object Navigator {

    fun goToDetail(currentActivity: Activity, superhero: Superhero) {
        val intent = Intent(currentActivity, SuperheroDetailActivity::class.java)
        intent.putExtra(SuperheroDetailActivity.EXTRA_SUPERHERO, superhero)
        currentActivity.startActivity(intent)
        currentActivity.overridePendingTransition(R.anim.enter_from_bottom, R.anim.nothing)
    }

}
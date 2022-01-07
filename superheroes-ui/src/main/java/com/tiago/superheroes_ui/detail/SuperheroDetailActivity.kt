package com.tiago.superheroes_ui.detail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_ui.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.livedata.observeAsState

@AndroidEntryPoint
class SuperheroDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SUPERHERO = "EXTRA_SUPERHERO"
    }

    val viewModel: SuperheroDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroDetailScreen()
        }
    }

    @Composable
    private fun SuperheroDetailScreen() {
        val superhero by viewModel.uiState.observeAsState()
        SuperheroDetailView(superhero!!)
    }

    @Composable
    private fun SuperheroDetailView(superhero: Superhero) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            BoxWithConstraints(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = superhero.imageUrl
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(this.maxWidth)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_close_24),
                    contentDescription = stringResource(R.string.close_description),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 22.dp, top = 50.dp)
                        .clickable {
                            finish()
                        }
                )
            }

            Text(
                text = superhero.name,
                fontSize = 34.sp,
                modifier = Modifier.padding(start = 16.dp, top = 32.dp)
            )
            val isInSquad = superhero.isInSquad
            Button(
                onClick = {
                    viewModel.onSquadButtonClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(
                        if (isInSquad)
                            R.color.button_background_important
                        else
                            R.color.button_background_default
                    )
                )
            ) {
                Text(
                    stringResource(
                        if (isInSquad) {
                            R.string.fire_from_squad_action
                        } else {
                            R.string.hire_to_squad_action
                        }
                    ),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            Text(
                if (superhero.description.isEmpty()) stringResource(R.string.no_description_label)
                else superhero.description,
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.nothing, R.anim.exit_to_bottom)
    }

}
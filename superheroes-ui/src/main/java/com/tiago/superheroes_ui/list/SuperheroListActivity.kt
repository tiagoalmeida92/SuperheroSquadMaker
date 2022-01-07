package com.tiago.superheroes_ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.tiago.superheroes_domain.Superhero
import com.tiago.superheroes_ui.Navigator
import com.tiago.superheroes_ui.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperheroListActivity : AppCompatActivity() {

    val viewModel: SuperheroListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroListScreen()
        }
    }

    @Composable
    private fun SuperheroListScreen() {
        val state by viewModel.uiState.collectAsState()
        when (state) {
            is SuperheroListState.Data -> {
                SuperheroListView(state as SuperheroListState.Data)
            }
            SuperheroListState.Loading -> {
                LoadingView()
            }
        }
    }

    @Composable
    private fun SuperheroListView(superheroListState: SuperheroListState.Data) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (superheroListState.squad.isNotEmpty()) {
                Text(
                    stringResource(R.string.squad_header_label),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                )
                //Squad
                LazyRow(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        bottom = 16.dp,
                        end = 16.dp
                    ),
                    content = {
                        superheroListState.squad.forEach {
                            item {
                                Column(
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .width(64.dp)
                                        .clickable {
                                            Navigator.goToDetail(
                                                this@SuperheroListActivity,
                                                it
                                            )
                                        }
                                ) {
                                    Image(
                                        painter = rememberImagePainter(
                                            data = it.imageUrl,
                                            builder = {
                                                transformations(CircleCropTransformation())
                                            }
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(64.dp)
                                    )
                                    Text(
                                        it.name,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                            .align(CenterHorizontally)
                                    )
                                }
                            }
                        }
                    })
            }
            LazyColumn(content = {
                superheroListState.superheroes.forEach {
                    item {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                Navigator.goToDetail(this@SuperheroListActivity, it)
                            }) {
                            Image(
                                painter = rememberImagePainter(
                                    data = it.imageUrl,
                                    builder = {
                                        transformations(CircleCropTransformation())
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(40.dp)

                            )
                            Text(
                                it.name,
                                modifier = Modifier.align(CenterVertically),
                                fontSize = 16.sp
                            )
                        }
                        Divider(
                            color = colorResource(R.color.divider),
                            modifier = Modifier.padding(start = 72.dp)
                        )

                    }
                }
            })
        }
    }

    @Composable
    private fun LoadingView() {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }

    @Preview
    @Composable
    fun PreviewSuperheroListView() {
        SuperheroListView(
            SuperheroListState.Data(
                listOf(
                    Superhero(1, "Hulk", "Green avenger", "", true)
                ),
                listOf(
                    Superhero(1, "Hulk", "Green avenger", "", true),
                    Superhero(2, "Thor", "Norse avenger", "", false)
                )
            )
        )
    }

}
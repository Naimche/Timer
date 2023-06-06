package com.naim.timer.screens.game

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.naim.timer.music.MusicViewModel
import com.naim.timer.navigation.AppScreens
import com.naim.timer.screens.game.ingame.GameViewModel
import com.naim.timer.screens.game.utils.ExposedDropMenuTimer
import com.naim.timer.screens.game.utils.HelpButton
import com.naim.timer.screens.game.utils.ImageCarousel
import com.naim.timer.screens.game.utils.TeamField
import com.naim.timer.screens.game.utils.Titulo
import com.naim.timer.screens.loginandreg.*
import com.naim.timer.ui.theme.Lobster

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameLobby(navController: NavController) {
    GameLobbyBodyContent(navController)
}

@Composable
fun GameLobbyBodyContent(
    navController: NavController,
    musicViewModel: MusicViewModel = hiltViewModel(),
    viewModel: GameLobbyViewModel = hiltViewModel()
) {


    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (viewModel.isMenuExtended) 1f else 0f, animationSpec = tween(
            durationMillis = 1000, easing = LinearEasing
        )
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (viewModel.isMenuExtended) 1f else 0f, animationSpec = tween(
            durationMillis = 400, easing = LinearEasing
        )
    )

    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }

    GameLobbyBodyContent(
        navController = navController,
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        clickAnimationProgress = clickAnimationProgress,
    ) {
        viewModel.isMenuExtended = viewModel.isMenuExtended.not()


    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameLobbyBodyContent(
    viewModel: GameLobbyViewModel = hiltViewModel(),
    circularTimerViewModel: GameViewModel = hiltViewModel(),
    navController: NavController,
    musicViewModel: MusicViewModel = hiltViewModel(),
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { },


    ) {
    Surface(modifier = Modifier.fillMaxSize()) {
        viewModel.updateCategories()
        Background()

        //region start configurationGame
        when (viewModel.whIsMenu) {

            0 -> {
                //region start configurationGame
                TimerSettings.teamName = viewModel.teamName1
                TimerSettings.teamName2 = viewModel.teamName2
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(bottom = 70.dp)
                ) {
                    Logo()
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        backgroundColor = Color(0xCEFFD7D7).copy(alpha = 0.2f),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(15.dp)
                            .padding(horizontal = 25.dp, vertical = 16.dp)
                            .wrapContentSize(),

                        ) {
                        Text(
                            text = "¡Bienvenido a Timer!",
                            modifier = Modifier.padding(
                                start = 16.dp, end = 10.dp, top = 16.dp, bottom = 16.dp
                            ),
                            style = TextStyle(color = Color.White),
                            fontSize = 26.sp,
                            fontFamily = Lobster,
                            color = Color(0xFFB885FF)
                        )
                    }
                    Button(
                        onClick = { navController.navigate("Game") },
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFfae079))
                    ) {
                        Text(
                            text = "Jugar ahora",
                            fontFamily = Lobster,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                    Row {
                        Checkbox(
                            checked = viewModel.ignoreCategories,
                            onCheckedChange = {
                                viewModel.ignoreCategories = viewModel.ignoreCategories.not()
                                TimerSettings.ignoreCategory = viewModel.ignoreCategories
                            },
                            modifier = Modifier.padding(top = 8.dp, end = 2.dp),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFFfae079),
                                uncheckedColor = Color(0xFFfae079),
                                checkmarkColor = Color(0xFFB885FF)
                            )
                        )
                        Text(
                            text = "Ignorar Categorias",
                            color = Color(0xFFB885FF),
                            fontSize = 20.sp,
                            fontFamily = Lobster,
                            modifier = Modifier.padding(top = 17.dp)
                        )
                        Spacer(modifier = Modifier.width(0.dp))
                        HelpButton("Si activas esta opción, el juego no tendrá en cuenta las categorías seleccionadas y se jugará con todas las categorías disponibles.")

                    }


                }

                //endregion
            }

            1 -> {

                //region start configurationGame
                Column(modifier = Modifier.fillMaxSize()) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(bottom = 20.dp, top = 70.dp)
                    ) {
                        Logo()
                        Spacer(modifier = Modifier.height(36.dp))
                    }


                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Row() {
                            Titulo(texto = "Equipo 1")
                            Spacer(modifier = Modifier.width(110.dp))
                            Titulo(texto = "Equipo 2")
                        }
                        Row {
                            TeamField(
                                onChange = { viewModel.teamName1 = it }, width = 155, "Equipo 1"
                            )
                            Spacer(modifier = Modifier.width(50.dp))
                            TeamField(
                                onChange = { viewModel.teamName2 = it }, width = 155, "Equipo 2"
                            )
                        }
                        Row {
                            Titulo(texto = "Categoría 1")
                            Spacer(modifier = Modifier.width(85.dp))
                            Titulo(texto = "Categoria 2")
                        }

                        Row {

                            ExposedDropMenuTimer(
                                expanded = viewModel.expanded,
                                onExpandedChange = {
                                    viewModel.expanded = viewModel.expanded.not()
                                },
                                onDismissRequest = { viewModel.expanded = false },
                                selectedCategory = viewModel.selectedCategory,

                                ) {
                                viewModel.categories.forEach { categ ->
                                    DropdownMenuItem(onClick = {
                                        viewModel.expanded = false
                                        viewModel.selectedCategory = categ
                                        TimerSettings.selectedCategory = categ
                                    }) {
                                        Text(text = categ)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.width(52.dp))
                            ExposedDropMenuTimer(
                                expanded = viewModel.expanded2,
                                onExpandedChange = {
                                    viewModel.expanded2 = viewModel.expanded2.not()
                                },
                                onDismissRequest = { viewModel.expanded2 = false },
                                selectedCategory = viewModel.selectedCategory2,
                            ) {
                                viewModel.categories.forEach { categ ->
                                    DropdownMenuItem(onClick = {
                                        viewModel.expanded2 = false
                                        viewModel.selectedCategory2 = categ
                                        TimerSettings.selectedCategory2 = categ
                                    }) {
                                        Text(text = categ)
                                    }
                                }
                            }


                        }


                        Row {
                            Titulo(texto = "Categoria 3")
                            Spacer(modifier = Modifier.width(85.dp))
                            Titulo(texto = "Timer")
                        }
                        Row {
                            ExposedDropMenuTimer(
                                expanded = viewModel.expanded3,
                                onExpandedChange = {
                                    viewModel.expanded3 = viewModel.expanded3.not()
                                },
                                onDismissRequest = { viewModel.expanded3 = false },
                                selectedCategory = viewModel.selectedCategory3,
                            ) {
                                viewModel.categories.forEach { categ ->
                                    DropdownMenuItem(onClick = {
                                        viewModel.expanded3 = false
                                        viewModel.selectedCategory3 = categ
                                        TimerSettings.selectedCategory3 = categ
                                    }) {
                                        Text(text = categ)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(52.dp))
                            ExposedDropMenuTimer(expanded = viewModel.expanded4,
                                onExpandedChange = {
                                    viewModel.expanded4 = viewModel.expanded4.not()
                                },
                                onDismissRequest = { viewModel.expanded4 = false },
                                selectedCategory = viewModel.selectedCategory4,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Timer,
                                        contentDescription = "Timer",
                                    )
                                }) {
                                viewModel.timer4.forEach { categ ->
                                    DropdownMenuItem(onClick = {
                                        viewModel.expanded4 = false
                                        viewModel.selectedCategory4 = categ
                                        TimerSettings.countDownInterval = categ.toLong() * 1000
                                        Log.i("Timer", circularTimerViewModel.initialTotalTime.toString())
                                    }) {
                                        Text(text = categ)
                                    }

                                }
                            }


                        }


                    }

                }


                //endregion
            }

            2 -> {
                //region start shop
                viewModel.updateDlcList()


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(bottom = 70.dp)
                ) {
                    Logo()
                    Spacer(modifier = Modifier.height(36.dp))
                    Row(
                        modifier = Modifier
                            .wrapContentHeight()
                            .background(Color(0xFFfae079))
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "¡Compra categorias!",
                            fontFamily = Lobster,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    ImageCarousel(dlcs = viewModel.dlcs)


                    Spacer(modifier = Modifier.height(86.dp))

                }
                //endregion
            }
        }

        //endregion


        //region start Navigation
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            CustomBottomNavigation(onclick1 = {
                navController.navigate(AppScreens.GameSettings.route)
            }, onclick2 = {navController.navigate(AppScreens.LoginScreen.route)})
            Circle(
                color = MaterialTheme.colors.primary.copy(alpha = 0.5f), animationProgress = 0.5f
            )

            FabGroup(renderEffect = renderEffect,
                animationProgress = fabAnimationProgress,
                onClick1 = {},
                onClick2 = { },
                onClick3 = {})
            FabGroup(renderEffect = null,
                animationProgress = fabAnimationProgress,
                toggleAnimation = toggleAnimation,
                onClick1 = { viewModel.whIsMenu = 0 },
                onClick2 = { viewModel.whIsMenu = 1 },
                onClick3 = { viewModel.whIsMenu = 2 })
            Circle(
                color = Color.White, animationProgress = clickAnimationProgress
            )
        }

        //endregion


    }


}


@Preview
@Composable
fun GameLobbyPreview() {
    val navController = rememberNavController()
    GameLobby(navController)
}

@Composable
fun ButtonGameLobby(navController: NavController, function: () -> Unit, text: String) {
    Button(
        onClick = function,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFfae079))
    ) {
        Text(text = text, color = Color.White)

    }
}






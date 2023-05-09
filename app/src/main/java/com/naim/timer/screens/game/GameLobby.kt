package com.naim.timer.screens.game

import android.annotation.SuppressLint
import android.os.Build
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
import com.google.rpc.Help
import com.naim.timer.music.MusicViewModel
import com.naim.timer.screens.game.utils.CountdownButton
import com.naim.timer.screens.game.utils.ExposedDropMenuTimer
import com.naim.timer.screens.game.utils.HelpButton
import com.naim.timer.screens.game.utils.ImageCarousel
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
        targetValue = if (viewModel.isMenuExtended) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (viewModel.isMenuExtended) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
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
    navController: NavController,
    musicViewModel: MusicViewModel = hiltViewModel(),
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { },


    ) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Background()
        //region start configurationGame
        when (viewModel.whIsMenu) {

            0 -> {
                //region start configurationGame
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
                            text = "¡Bienvenido a Timer, el juego de mesa Times Up ahora en tu móvil! Adivina palabras y frases antes de que se acabe el tiempo!",
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 10.dp,
                                top = 16.dp,
                                bottom = 16.dp
                            ),
                            style = TextStyle(color = Color.White),
                            fontSize = 26.sp,
                            fontFamily = Lobster,
                            color = Color(0xFFB885FF)
                        )
                    }
                    Button(
                        onClick = { /* acción al pulsar el botón */ },
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
                            Text(
                                text = "Categoría 1",
                                modifier = Modifier.padding(
                                    start = 0.dp,
                                    end = 10.dp,
                                    top = 16.dp,
                                    bottom = 8.dp
                                ),
                                style = TextStyle(color = Color.White),
                                fontSize = 26.sp,
                                fontFamily = Lobster,
                                color = Color(0xFFB885FF)
                            )
                            Text(
                                text = "Categoría 2",
                                modifier = Modifier.padding(
                                    start = 85.dp,
                                    end = 10.dp,
                                    top = 16.dp,
                                    bottom = 8.dp
                                ),
                                style = TextStyle(color = Color.White),
                                fontSize = 26.sp,
                                fontFamily = Lobster,
                                color = Color(0xFFB885FF)
                            )
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
                                viewModel.categories2.forEach { categ ->
                                    DropdownMenuItem(onClick = {
                                        viewModel.expanded2 = false
                                        viewModel.selectedCategory2 = categ
                                    }) {
                                        Text(text = categ)
                                    }
                                }
                            }


                        }


                        Row {
                            Text(
                                text = "Categoría 3",
                                modifier = Modifier.padding(
                                    start = 0.dp,
                                    end = 10.dp,
                                    top = 16.dp,
                                    bottom = 8.dp
                                ),
                                style = TextStyle(color = Color.White),
                                fontSize = 26.sp,
                                fontFamily = Lobster,
                                color = Color(0xFFB885FF)
                            )

                            Text(
                                text = "Timer",
                                modifier = Modifier.padding(
                                    start = 85.dp,
                                    end = 10.dp,
                                    top = 16.dp,
                                    bottom = 8.dp
                                ),
                                style = TextStyle(color = Color.White),
                                fontSize = 26.sp,
                                fontFamily = Lobster,
                                color = Color(0xFFB885FF)
                            )
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
                                    viewModel.categories3.forEach { categ ->
                                        DropdownMenuItem(onClick = {
                                            viewModel.expanded3 = false
                                            viewModel.selectedCategory3 = categ
                                        }) {
                                            Text(text = categ)
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.width(52.dp))
                                ExposedDropMenuTimer(
                                    expanded = viewModel.expanded4,
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
                                    }
                                ) {
                                    viewModel.timer4.forEach { categ ->
                                        DropdownMenuItem(onClick = {
                                            viewModel.expanded4 = false
                                            viewModel.selectedCategory4 = categ
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

                    ImageCarousel(dlcs = viewModel.Dlcs)


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

            CustomBottomNavigation()
            Circle(
                color = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                animationProgress = 0.5f
            )

            FabGroup(
                renderEffect = renderEffect,
                animationProgress = fabAnimationProgress,
                onClick1 = { },
                onClick2 = { },
                onClick3 = {})
            FabGroup(
                renderEffect = null,
                animationProgress = fabAnimationProgress,
                toggleAnimation = toggleAnimation,
                onClick1 = { viewModel.whIsMenu = 0 },
                onClick2 = { viewModel.whIsMenu = 1 },
                onClick3 = { viewModel.whIsMenu = 2 }
            )
            Circle(
                color = Color.White,
                animationProgress = clickAnimationProgress
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






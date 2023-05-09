package com.naim.timer.screens.game.utils

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.naim.timer.model.DataWords

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageCarousel(dlcs : List<DataWords>) {

    val listState = rememberLazyListState()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-56).dp)
            .padding(top = 90.dp, bottom = 0.dp),
        horizontalArrangement = Arrangement.Center,
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
        ,
        content = {
            items(dlcs.size) { index ->
                val imagen = dlcs[index]
                Card(
                    modifier = Modifier.padding(16.dp),
                    onClick =( { /* Lógica para abrir ventana de compra */ }),
                    enabled = true
                ) {
                    Column(
                        modifier = Modifier.size(238.dp),
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(imagen.urlImg),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()

                        )
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterialApi::class)

@Composable
fun ExposedDropdownMenuBox_SelectionStyling(categories : List<String>) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableStateOf(0) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.padding(16.dp),
    ) {
        TextField(
            value = categories[selectedItemIndex],
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        , modifier = Modifier
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEachIndexed { index, item ->
                DropdownMenuItem(

                    onClick = {
                        selectedItemIndex = index
                        expanded = false
                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                    }
                ){
                    Text(
                        text = item,
                        fontWeight = if (index == selectedItemIndex) FontWeight.Bold else null
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExposedDropMenuTimer(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    selectedCategory: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange =
             onExpandedChange,
        modifier = Modifier.width(155.dp)
    ) {

            TextField(
                readOnly = true, // (3)
                value = selectedCategory, // (4)
                onValueChange = { },
                leadingIcon = leadingIcon,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon( // (5)
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                textStyle = TextStyle(fontSize = 18.sp)
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = onDismissRequest, content = content)

        }


    }


@Composable
fun CountdownButton(
    onCountdownSelected: (Int) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    selectedCategory: String,) {

    Column() {
        ExposedDropMenuTimer(
            expanded = expanded ,
            onExpandedChange = onExpandedChange,
            onDismissRequest = { /*TODO*/ },
            selectedCategory = selectedCategory
        ) {

        }
    }

}
@Composable
fun HelpButton() {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.Help,
            contentDescription = "Help button icon",
            modifier = Modifier.size(54.dp),
            tint = Color.White.copy(alpha = ContentAlpha.medium)
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .widthIn(max = 200.dp)
            .wrapContentSize(Alignment.TopEnd),
        offset = DpOffset(170.dp, 10.dp),
    ) {
        Column {
            Text("Este es un botón de ayuda.", modifier = Modifier.padding(16.dp))

        }
    }
}





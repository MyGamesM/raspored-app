package com.example.raspored.elements

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.raspored.dataProcessor

class RasporedTableViewModel: ViewModel() {
    var grupa by mutableIntStateOf(dataProcessor.grupa)
    var day by mutableIntStateOf(dataProcessor.getDay())
    fun updateGrupaState() { grupa = dataProcessor.grupa }
    fun updateDayState() { day = dataProcessor.getDay(0) }
}

@Composable
fun RasporedTable(viewModel: RasporedTableViewModel, modifier: Modifier = Modifier) {
    val special_classes: Array<String> = arrayOf("Matematika", "Matematika I", "Sociologija", "Programiranje", "Gradjansko")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xff000000)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(Color(0xff171717))
                .padding(5.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            contentPadding = PaddingValues(all = 10.dp)
        ) {
            dataProcessor.raspored[viewModel.day].forEachIndexed { i, it ->
                item { Text(
                        text = dataProcessor.startTimeTable[i],
                        color = Color(0xfffafafa),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    val text: String = if (it != "g") it else dataProcessor.grupe[viewModel.day][viewModel.grupa][i]
                    val fontSize = if (special_classes.contains(text)) 12 else 18
                    Text(
                        text = text,
                        fontSize = fontSize.sp,
                        color = Color(0xfffafafa),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        softWrap = false,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                item { Text(
                        text = dataProcessor.endTimeTable[i],
                        color = Color(0xfffafafa),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
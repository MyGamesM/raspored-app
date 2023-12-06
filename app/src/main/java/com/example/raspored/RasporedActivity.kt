package com.example.raspored

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raspored.elements.RasporedButton
import com.example.raspored.elements.RasporedTable
import com.example.raspored.elements.RasporedTableViewModel
import com.example.raspored.ui.theme.RasporedTheme

class RasporedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RasporedTheme {
                Homescreen(RasporedTableViewModel())
            }
        }
    }
}

val dani: Array<String> = arrayOf("Ponedeljak", "Utorak", "Sreda", "Cetvrtak", "Petak")

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun Homescreen(viewModel: RasporedTableViewModel) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff000000)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Raspored",
            fontSize = 32.sp,
            color = Color(0xfffafafa),
            modifier = Modifier.padding(top = 10.dp)
        )
        RasporedTable(viewModel)
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(Color(0xff171717))
                .padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            dani.forEachIndexed { i, it ->
                RasporedButton(
                    onClick = {
                        dataProcessor.setDay(i)
                        viewModel.updateDayState()
                    },
                    modifier = Modifier.weight(1f),
                    text = it
                )
            }
            RasporedButton(
                onClick = {
                    viewModel.updateDayState()
                },
                modifier = Modifier.weight(1f),
                text = "Refresh",
            )
        }
    }
}
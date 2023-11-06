package com.example.raspored

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raspored.ui.theme.RasporedTheme

class DebuggingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RasporedTheme {
                Homescreen()
            }
        }
    }
}

@Preview
@Composable
private fun Homescreen() {
    val data: MutableList<Array<String>> = arrayListOf()
    for (i in 8..14) {
        if (i > 9) {
            data.add(arrayOf("$i", "00"))
            data.add(arrayOf("$i", "15"))
            data.add(arrayOf("$i", "30"))
            data.add(arrayOf("$i", "45"))
        } else {
            data.add(arrayOf("0$i", "00"))
            data.add(arrayOf("0$i", "15"))
            data.add(arrayOf("0$i", "30"))
            data.add(arrayOf("0$i", "45"))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff000000))
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(Color(0xff171717))
                .padding(25.dp)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dataProcessor.getClass(1),
                fontSize = 32.sp,
                color = Color(0xfffafafa)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(Color(0xff171717))
                .padding(25.dp)
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fontSize = 20
            data.forEach {
                Row {
                    Text(
                        text = "${it[0]}:${it[1]}",
                        color = Color(0xfffafafa),
                        fontSize = fontSize.sp,
                        modifier = Modifier
                            .padding(end = 20.dp)
                    )
                    Text(
                        text = dataProcessor.getCurrentTime(1, it[0], it[1]).toString(),
                        color = Color(0xfffafafa),
                        fontSize = fontSize.sp,
                        modifier = Modifier
                            .padding(end = 20.dp)
                    )
                }
            }
        }
    }
}
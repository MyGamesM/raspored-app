package com.example.raspored

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raspored.ui.theme.RasporedTheme

class SettingActivity : ComponentActivity() {
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff000000)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = 32.sp,
            color = Color.White,
        )
        Column(
            modifier = Modifier
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(Color(0xff171717))
                .padding(10.dp),
        ) {
            Text(
                text = "Grupa:",
                color = Color(0xfffafafa),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 5.dp)
            )
            RadioButtons()
        }
//        Column(
//            modifier = Modifier
//                .padding(10.dp, 0.dp, 10.dp, 0.dp)
//                .clip(shape = RoundedCornerShape(25.dp))
//                .background(Color(0xff171717))
//                .padding(10.dp)
//                .fillMaxWidth(),
//        ) {
//            var text by remember { mutableStateOf("12345") }
//            BasicTextField(
//                value = text,
//                onValueChange = { text = it },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                textStyle = LocalTextStyle.current.copy(
//                    color = Color(0xfffafafa),
//                    fontSize = 18.sp
//                )
//            )
//        }
    }
}
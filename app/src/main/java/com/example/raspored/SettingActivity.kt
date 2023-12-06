package com.example.raspored

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.raspored.room.SelectedDatabase
import com.example.raspored.room.SelectedState
import com.example.raspored.room.SelectedViewModel
import com.example.raspored.ui.theme.RasporedTheme

class SettingActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            SelectedDatabase::class.java,
            "selected.db"
        ).build()
    }

    private val viewModel by viewModels<SelectedViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SelectedViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state by viewModel.state.collectAsState()
            RasporedTheme {
                Homescreen(state, viewModel)
            }
        }
    }
}

@Composable
private fun Homescreen(
    state: SelectedState,
    viewModel: SelectedViewModel
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    val selected: Int = state.selected.selected.toIntOrNull() ?: 1

    val radioButtons = remember {
        mutableStateListOf(
            ToggleableInfo(
                isChecked = selected == 0,
                text = "Prva"
            ),
            ToggleableInfo(
                isChecked = selected == 1,
                text = "Druga"
            ),
            ToggleableInfo(
                isChecked = selected == 2,
                text = "Treca"
            )
        )
    }

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
            radioButtons.forEachIndexed { index, info ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            dataProcessor.grupa = index
                            viewModel.setSelectedValue(index.toString())
                            radioButtons.replaceAll {
                                it.copy(
                                    isChecked = it.text == info.text
                                )
                            }
                        }
                ) {
                    RadioButton(
                        selected = info.isChecked,
                        onClick = {
                            dataProcessor.grupa = index
                            viewModel.setSelectedValue(index.toString())
                            radioButtons.replaceAll {
                                it.copy(
                                    isChecked = it.text == info.text
                                )
                            }
                        }
                    )
                    Text(
                        text = info.text,
                        color = Color(0xfffafafa),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
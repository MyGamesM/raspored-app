package com.example.raspored

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ToggleableInfo(
    val isChecked: Boolean,
    val text: String
)
//  FUCK THIS FUCKING DATA STORE SHIT; FIND A WAY TO USE TEXT FILES

//private lateinit var dataStore: DataStore<Preferences>
//
//private suspend fun Save(key: String, value: ToggleableInfo) {
//    val dataStoreKey = stringPreferencesKey(key)
//    dataStore.edit { settings ->
//        settings[dataStoreKey] = value.toString()
//    }
//}
//
//private suspend fun Load(key: String): String? {
//    val dataStoreKey = stringPreferencesKey(key)
//    val preferences = dataStore.data.first()
//    return preferences[dataStoreKey]
//}

@Composable
fun RadioButtons() {
    val radioButtons = remember {
        mutableStateListOf(
            ToggleableInfo(
                isChecked = true,
                text = "Prva"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Druga"
            ),
            ToggleableInfo(
                isChecked = false,
                text = "Treca"
            )
        )
    }

    radioButtons.forEachIndexed { index, info ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    dataProcessor.grupa = index
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.text == info.text
                        )
                    }
                }
                .padding(end = 16.dp)
                .fillMaxWidth()
        ) {
            RadioButton(
                selected = info.isChecked,
                onClick = {
                    dataProcessor.grupa = index
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
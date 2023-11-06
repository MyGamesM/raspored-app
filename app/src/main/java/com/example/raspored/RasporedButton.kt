package com.example.raspored

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun RasporedButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    fontSize: Int = 18
) {
    var fontSizeState: Int by remember { mutableIntStateOf(fontSize) }
    var readyToDraw by remember { mutableStateOf(false) }
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = fontSizeState.sp,
            color = Color(0xfffafafa),
            textAlign = TextAlign.Center,
            maxLines = 1,
            softWrap = false,
            modifier = Modifier.drawWithContent {
                if (readyToDraw) drawContent()
            },
            onTextLayout = { textLayoutResult: TextLayoutResult ->
                if (textLayoutResult.didOverflowWidth) {
                    fontSizeState -= 1
                } else {
                    readyToDraw = true
                }
            }
        )
    }
}
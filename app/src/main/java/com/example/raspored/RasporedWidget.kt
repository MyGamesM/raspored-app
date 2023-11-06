package com.example.raspored

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.text.Text
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

object RasporedWidget : GlanceAppWidget() {
    val classKey = stringPreferencesKey("txt")

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Row {
                Widget()
            }
        }
    }
}

@Composable
fun Widget() {
    val txt = currentState(RasporedWidget.classKey) ?: "Empty"
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color(0xff272727)),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
    ) {
        Text(
            text = txt,
            style = TextStyle(
                fontSize = 26.sp,
                color = ColorProvider(Color.White)
            )
        )
        Button(
            text = "Refresh",
            onClick = actionRunCallback(RefreshActionCallback::class.java)
        )
    }
}

class RasporedWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = RasporedWidget
}

class RefreshActionCallback: ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prefs ->
            prefs[RasporedWidget.classKey] = dataProcessor.getClass(0)
        }
        RasporedWidget.update(context, glanceId)
    }
}
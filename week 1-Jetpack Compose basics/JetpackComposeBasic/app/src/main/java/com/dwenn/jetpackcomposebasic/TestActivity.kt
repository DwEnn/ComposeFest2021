package com.dwenn.jetpackcomposebasic

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import com.dwenn.jetpackcomposebasic.ui.theme.JetpackComposeBasicTheme

class TestActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBasicTheme {
                Button(onClick = { startActivity(Intent(this@TestActivity, MainActivity::class.java)) }) {
                    Text(text = "start MainActivity")
                }
            }
        }
    }

}

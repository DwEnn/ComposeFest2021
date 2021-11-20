package com.dwenn.layoutincompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.dwenn.layoutincompose.ui.theme.LayoutInComposeTheme

class LayoutCodeLabActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutInComposeTheme {
                LayoutCodeLab()
            }
        }
    }
}

@Composable
fun LayoutCodeLab() {
    Text(text = "Hi there !")
}

@Composable
fun LayoutCodeLabPreview() {
    LayoutInComposeTheme {

    }
}
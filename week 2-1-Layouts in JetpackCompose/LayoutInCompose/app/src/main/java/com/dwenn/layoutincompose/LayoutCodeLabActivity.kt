package com.dwenn.layoutincompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(text = "Hi there!")
            Text(text = "Thanks for going through the Layouts codelab")
        }
    }
}

@Preview
@Composable
fun LayoutCodeLabPreview() {
    LayoutInComposeTheme {
        LayoutCodeLab()
    }
}
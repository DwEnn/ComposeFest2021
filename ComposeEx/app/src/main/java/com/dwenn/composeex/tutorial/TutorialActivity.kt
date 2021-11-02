package com.dwenn.composeex.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class TutorialActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Text("Hello world!")
//            MessageCard("Android")
            MessageCard2(Message("Android", "Jetpack Compose"))
        }
    }

    /**
     * Preview annotation 으로 UI 구성요소 미리보기를 볼 수 있다.
     * (파라미터 없이 구성된 함수여야함)
     */
    @Preview
    @Composable
    fun PreviewMessageCard() {
//        MessageCard(name = "Dear")
        MessageCard2(Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!"))
    }

    @Composable
    private fun MessageCard(name: String) {
        Text(text = "Hello $name!")
    }

    /*
        2. Layouts
        - UI 요소는 계층적이며 다른 요소에 포함된 요소가 있다.
          Compose 에서는 다른 구성 가능한 함수로부터 구성 가능한 함수를 호출하여 UI 구조를 빌드한다.
     */

    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard2(msg: Message) {
        /*
            정렬 방법에 관한 정보가 제공되지 않았으므로 텍스트 요소가 서로 겹치게 표시된다.
         */
        Text(text = msg.author)
        Text(text = msg.body)
    }

}
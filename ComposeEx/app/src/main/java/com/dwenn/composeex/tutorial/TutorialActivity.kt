package com.dwenn.composeex.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dwenn.composeex.R

class TutorialActivity : ComponentActivity() {

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
//        MessageCard2(Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!"))
//        UserData()
        AlignInRow()
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
//        Text(text = msg.author)
//        Text(text = msg.body)

        /*
            Column 함수 이용하여 요직을 수직으로 정렬
            - Row 를 이용하여 항목을 가로로 정렬하고 Box 를 사용하여 요소를 쌓을 수 있다.
         */
        Row {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Contact profile picture"
            )

            Column {
                Text(text = msg.author)
                Text(text = msg.body)
            }
        }
    }

    /**
     * compose layout 을 활용하는 또 다른 예제
     * @link https://gift123.tistory.com/41
     * Row 는 수평방향으로 UI 를 정렬한다.
     * Column 은 수직방향으로 UI 를 정렬한다.
     */
    @Composable
    private fun UserData() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp, 12.dp, 8.dp, 12.dp)
                    .size(44.dp)
                    .clip(shape = CircleShape)
            )
            Column {
                Text(text = "name")
                Text(
                    text = "lastSeenOnline",
                    Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp)
                )
            }
        }
    }

    @Composable
    private fun AlignInRow() {
        Row(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Yellow),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(50.dp)
                    .background(Color.Red)
            )
            Box(
                Modifier
                    .size(50.dp)
                    .background(Color.Green),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color.Blue)
                )
            }
        }
    }

}
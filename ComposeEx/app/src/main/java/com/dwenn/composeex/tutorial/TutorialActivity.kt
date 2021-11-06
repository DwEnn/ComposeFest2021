package com.dwenn.composeex.tutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dwenn.composeex.R
import com.dwenn.composeex.ui.theme.ComposeExTheme

class TutorialActivity : ComponentActivity() {

    private val mMessages = listOf<Message>(
        Message("Colleague1", "Hey, take a look at Jetpack Compose, it's great!\nis Expanded"),
        Message(
            "Colleague2", "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "is Expanded"
        ),
        Message(
            "Colleague3", "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "is Expanded"
        ),
        Message(
            "Colleague4", "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "is Expanded"
        ),
        Message(
            "Colleague5", "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "is Expanded"
        ),
        Message(
            "Colleague6", "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "is Expanded"
        ),
        Message(
            "Colleague7", "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "is Expanded"
        ),
        Message(
            "Colleague8", "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "is Expanded"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Text("Hello world!")
//            MessageCard("Android")
//            MessageCard2(Message("Android", "Jetpack Compose"))
            ComposeExTheme {
//                MessageCard6(
//                    msg = Message(
//                        "Colleague",
//                        "Hey, take a look at Jetpack Compose, it's great!"
//                    )
//                )
                Conversation(messages = mMessages)
            }
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
//        AlignInRow()
        val msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
//        MessageCard3(msg = msg)
//        MessageCard4(msg = msg)
        MessageCard5(msg = msg)
    }

    @Composable
    private fun MessageCard(name: String) {
        Text(text = "Hello $name!")
    }

    /*
        Column
        - Column 함수를 사용하면 요소를 수직으로 정렬할 수 있다.
        - 행을 사용하여 항목을 가로로 정렬하고 Box 를 사용하여 요소를 쌓을 수 있다.
     */

    @Composable
    fun MessageCard3(msg: Message) {
        Column {
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }

    /*
        이미지 요소 추가
        - Resource Manager 를 사용하여 사진 라이브러리에서 이미지를 가져오거나 내부 이미지 사용 가능
     */

    @Composable
    fun MessageCard4(msg: Message) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Contact profile picture"
            )

            Column {
                Text(text = msg.author)
                Text(text = msg.body)
            }
        }
    }

    /*
        레이아웃 구성
        - 메시지 레이아웃의 구조는 올바르지만 요소의 간격이 균등하지 않고 이미지가 너무 크다.
          Composable 을 장식하거나 구성하기 위해 Compose 는 Modifier 를 사용한다.
        Modifier
        - Composable 의 크기, 레이아웃, 모양을 변경하거나 요소를 클릭 가능하게 만드는 등의 상위 수준 상호작용을 추가할 수 있다.
          연결하여 보다 복잡한 Composable 을 만들 수 있다.
     */

    @Composable
    fun MessageCard5(msg: Message) {
        // Add padding around our Message
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp) // Set image size
                    .clip(CircleShape) // Clip image to shape
            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = msg.author)
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = msg.body)
            }
        }
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

    /*
        3. 머티리얼 디자인
        - Compose 는 머티리얼 디자인 원칙을 지원하도록 빌드되었다.
          많은 UI 요소가 머티리얼 디자인을 즉시 사용 가능하도록 구현한다.
          이 강의에서는 머티리얼 위젯으로 앱의 스타일을 지정한다.
     */

    /*
        Material design 사용
        - 프로젝트에서 생성한 Material theme 로 Composable function 을 래핑한다.
        * Empty Compose Activity 는 MaterialTheme 을 맞춤설정할 수 있는 프로젝트의 기본 테마를 생성한다.
     */

    @Preview
    @Composable
    fun PreviewMaterialDesign() {
        ComposeExTheme {
            val msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
            MessageCard5(msg = msg)
        }
    }

    /*
        색상(color)
        - 래핑된 테마의 색상으로 간단하게 스타일을 지정할 수 있으며 색상이 필요한 모든 곳에 이 테마의 값을 사용할 수 있다.
        서체(style)
        - Material 서체 스타일은 MaterialTheme 에서 사용할 수 있으며 텍스트 Composable 에 추가하기만 하면 된다.
        도형(surface)
        - 도형을 사용하여 최종 터치를 추가할 수 있다.
        또한 더 나은 레이아웃을 위해 메시지에 패딩을 추가하기도 한다.
     */
    @Composable
    fun MessageCard6(msg: Message) {
        // Add padding around our Message
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp) // Set image size
                    .clip(CircleShape) // Clip image to shape
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))

                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }

    /*
        어두운 테마 사용
        - Jetpack Compose 는 기본적으로 어두운 테마를 처리할 수 있다.
        Material 색상, 텍스트, 배경을 사용하면 어두운 배경에 맞춰 자동으로 조정된다.
        LightTheme, DarkTheme 의 색상 선택은 생성된 Theme.kt 파일에 정의되어 있다.
     */

    /*
        파일에서 별도의 함수로 여러 미리보기를 만들거나 동일한 함수에 여러 주석을 추가할 수 있다.
     */
    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewMessageCard2() {
        ComposeExTheme {
            MessageCard6(
                msg = Message(
                    "Colleague",
                    "Hey, take a look at Jetpack Compose, it's great!"
                )
            )
        }
    }

    /*
        4. 목록 및 애니메이션
        LazyColumn, LazyRow
        - 화면에 표시되는 요소만 렌더링하므로 긴 목록에 매우 효율적으로 설계되었다.
        동시에 XML 레이아웃으로 RecyclerView 의 복잡성을 피한다.
     */

    @Composable
    fun Conversation(messages: List<Message>) {
        // LazyColumn 의 하위 요소가 List 를 매개변수로 가져오고 lambda 가 Message 를 수신한다.
        LazyColumn {
            items(messages) { message ->
//                MessageCard5(msg = message)
                MessageCard7(msg = message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        ComposeExTheme {
            Conversation(
                messages = listOf(
                    Message("Collage", "empty body"),
                    Message("Collage", "empty body\nempty body"),
                    Message(
                        "Collage", "empty body\nempty body\n" +
                                "empty body"
                    ),
                )
            )
        }
    }

    /*
        확장 중 메시지에 애니메이션 적용
        - 메시지를 확장하여 더 길게 보여주고 컨텐츠 크기와 배경 색상 모두에 애니메이션 효과를 적용할 것이다.
        이 로컬 UI 상태를 저장하려면 메시지가 확장되었는지 여부를 추적해야 한다.
        이를 위해 remember 및 mutableStateOf 함수를 사용해야 한다.

        구성 가능한 함수는 remember 를 사용하여 메모리에 로컬 상태를 저장하고 mutableStateOf 에 전달된 값의 변경사항을 추적할 수 있다.
        이 상태를 사용하는 Composable 및 하위 요소는 값이 업데이트되면 자동으로 다시 그려진다.
        이를 재구성(ReComposable) 이라고 한다.

        remember 및 mutableStateOf 와 같은 Compose 의 상태 API 를 사용하여 상태를 변경하면 UI 가 자동으로 업데이트된다.
     */

    @Composable
    fun MessageCard7(msg: Message) {
        // Add padding around our Message
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp) // Set image size
                    .clip(CircleShape) // Clip image to shape
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))

            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by remember { mutableStateOf(false) }
            // surfaceColor will be updated gradually from one color to the other
            val surfaceColor: Color by animateColorAsState(
                targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )

            // We toggle the isExpanded variable when we click on this Column
            Column(
                modifier = Modifier.clickable { isExpanded = !isExpanded }
            ) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))

                // animateContentSize Modifier 를 사용하여 메시지 컨테이너 크기에 부드럽게 애니메이션을 적용
                Surface(
                    shape = MaterialTheme.shapes.medium, elevation = 1.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }

}


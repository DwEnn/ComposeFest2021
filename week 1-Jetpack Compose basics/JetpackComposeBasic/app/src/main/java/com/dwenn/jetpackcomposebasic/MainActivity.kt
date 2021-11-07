package com.dwenn.jetpackcomposebasic

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dwenn.jetpackcomposebasic.ui.theme.JetpackComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                    MyApp()
//                    MyApp2()
//                    MyApp3()
//                    MyApp4()
                    MyApp5()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    // change color with Surface
    Surface(color = MaterialTheme.colors.primary) {
        Text(text = "Hello $name!")
    }
    /*
        Preview 를 보면 텍스트가 흰색으로 바뀐것을 알 수 있다.
        하지만 우리는 이것을 정의하지 않았다.
        Material components(Surface, foundation 과 같은) 는 app components 를 유연하게 접근 가능하게 해준다.
        이 예제에서는 Surface 의 배경이 primary 색상으로 설정되면 그 위에 있는 Text 는 onPrimary 를 사용해야 한다는 것을 이해한다.
     */
}

/*
    Modifiers
    - 대부분의 Compose UI 요소들(Surface, Text 와 같은)은 modifier 라는 optional parameter 를 받는다.
    Modifiers 는 UI 요소를 어떻게 디스플레이하고 부모 레이아웃이 어떤 동작을 하는지 설명한다.
 */

@Composable
fun Greeting2(name: String) {
    // change color with Surface
    Surface(color = MaterialTheme.colors.primary) {
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}

/*
    5. Reusing Composable
    - UI 요소에 더 많은 components 를 추가하다보면 많은 중첩이 생기게 되며 함수가 커질수록 가독성이 떨어지게 된다.
    재사용가능한 작은 components 를 생성하면 앱에 사용되는 UI 요소 라이브러리를 쉽게 빌드할 수 있다.
    각 화면은 화면의 작은 부분을 담당하며 독립적으로 편집할 수 있다.

    MyApp Composable function 을 재사용하여 preview 와 onCreate() 를 깔끔하게 만들고
    코드 중첩을 피할 수 있다.
 */

@Composable
private fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
//        Greeting2(name = "Android")
        Greeting3(name = "Android")
    }
}

/*
    6. Creating columns and rows
    - 3가지 기본 layout 요소인 Column, Row, Box 가 존재한다.
    이것들은 내부에 Composable content 를 갖는 Composable function 으로 Column 의 경우 수직으로 요소를 배치한다.

    Composable function 은 다른 Kotlin function 와 같이 사용될 수 있다.
    이렇게 하면 UI 를 표시하는 방식에 영향을 미치는 로직을 추가할 수 있어 UI 구축에 매우 효과적이다.

    아직 수치 또는 제약 조건을 composable 에 설정하지 않았으므로 각 행들은
    요소의 minimum 크기를 갖고 @Preview 도 동일하다.

    * 참고
    - 다른 방법으로 padding 을 생성하는 것과 같이 Modifier 를 overloads 할 수 있다.
    - 요소에 여러 Modifier 를 추가하려면 Modifier 를 체인링하면 된다.
 */

@Composable
fun Greeting3(name: String) {
    // change color with Surface
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Hello,")
                Text(text = "$name!")
            }
            OutlinedButton(
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Show more")
            }
        }
    }
}

@Composable
private fun MyApp2(names: List<String> = listOf("World", "Compose", "Jetpack")) {
    Column {
//        for (name in names) Greeting3(name = name)
        for (name in names) Greeting4(name = name)
    }
}

/*
    7. State in Compose
    - State<T> 가 변경될 때 Remember Composable 객체 저장 -> 수정할 때 mutableStateOf<T> 로 접근
    초기 Composition -> 데이터의 변경 -> ReComposition
 */

@Composable
fun Greeting4(name: String) {
    val expanded = rememberSaveable {
        mutableStateOf(false)
    }
    // state 에 의존하고 간단한 계산을 수행하기 대문에 recomposition 을 위해
    // extraPadding 을 remember 할 필요는 없다.
//    val extraPadding = if (expanded.value) 48.dp else 0.dp
    // apply animate
    val extraPadding by animateDpAsState(
        targetValue = if (expanded.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    // change color with Surface
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
//                    .padding(bottom = extraPadding)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello,")
                Text(
                    text = "$name!", style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
            OutlinedButton(
                modifier = Modifier.align(
                    alignment = Alignment.CenterVertically
                ),
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(text = if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

/*
    8. State hoisting
    - Composable function 에서 여러 함수에 의해 읽거나 수정되는 상태는 공통 상위 항목에 존재해야 한다.
    이 프로세스를 state hoisting 이라고 한다.
 */

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    // this state should be hoisted
    // by 키워드를 사용하여 .value 를 매번 입력하지 않도록 할 수 있다.
//    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics CodeLab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                // showOnboarding 이 false 로 설정되지만 아직 상태를 읽을 수 없다.
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
private fun MyApp3(names: List<String> = listOf("World", "Compose", "Jetpack")) {
    var shouldShowOnboarding by remember {
        mutableStateOf(true)
    }
    if (shouldShowOnboarding) {
        OnboardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        Column {
            for (name in names) Greeting4(name = name)
        }
    }
}

/*
    9. Creating a performant lazy list
    - 이제 목록을 좀 더 현실적으로 만들어 볼것이다. 지금까지 하나의 Column 에 두 개의 요소를 배치하였다.
    하지만 그것이 수천개가 되면 처리할 수 있을까 ?

    1000개의 리스트를 만들어 요소를 배치하면 화면에 보이지 않는 요소가 1000개 만들어진다.
    이것은 분명히 성능에 좋지 않으며 에뮬레이터를 멈추게 할 수도 있다.

    스크롤 가능한 열을 표시하기 위해 LazyColumn 을 사용한다. LazyColumn 은 화면에 보이는 항목만 렌더링하므로
    큰 목록을 렌더링할 때 성능을 향상 시킬 수 있다.
    * 참고 : LazyColumn, LazyRow 는 안드로이드 RecyclerView 와 동일하다.
 */

@Composable
fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
//            Greeting4(name = name)
            Greeting5(name = name)
        }
    }
}

@Composable
private fun MyApp4(names: List<String> = listOf("World", "Compose", "Jetpack")) {
    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }
    if (shouldShowOnboarding) {
        OnboardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

/*
    10. Persisting state
    - 현재까지 예제의 문제점은 앱을 실행하고 버튼을 클릭하여 확장한 다음 앱을 회전하면 화면이 다시 표시 된다는 것이다.
    remember 함수는 Composition 안에 composable 이 보관되어 있는 경우에만 작동한다.
    화면이 회전하면 전체 작업이 다시 시작되므로 모든 상태가 손실된다.
    이 문제는 모든 구성 변경 및 프로세스 종료 시에도 발생한다.
    -> remember 를 사용하는 대신 rememberSaveable 을 사용할 수 있다.
    각 상태(예: 회전)에서 살아남은 구성 변경 사항 및 프로세스

 */


@Composable
private fun MyApp5() {
    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }
    if (shouldShowOnboarding) {
        OnboardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
fun Greeting5(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name = name)
    }
}

@Composable
fun CardContent(name: String) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name, style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, padding theme elit, sed do bouncy. ").repeat(
                        4
                    )
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) stringResource(id = R.string.show_less)
                else stringResource(id = R.string.show_more)
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Composable
fun DefaultPreview() {
    JetpackComposeBasicTheme {
//        Greeting("Android")
//        Greeting2("Android")
//        MyApp()
//        MyApp2()
//        MyApp4()
        Greetings()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 320
)
@Composable
fun OnboardingPreview() {
    JetpackComposeBasicTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}
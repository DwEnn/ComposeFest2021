package com.dwenn.layoutincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dwenn.layoutincompose.ui.theme.LayoutInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {

    /*
        사진을 로드하는 동안 보여줄 placeholder 를 설정한다.
        이를 위해 원 모양과 placeholder 색상을 지정하는 Surface 를 사용한다.
        크기를 지정하기위해 size modifier 를 사용
     */
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable {  }
            .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image
        }
        /*
            Row 의 composable 은 weight 또는 align 을 사용 가능하다.
            type-safety 를 지원하므로 다른 레이아웃에서 맞지않는 수정자를 적용할 수 없다.
            예를 들어 Box 에서는 weight 이 의미가 없으므로 compile-time error 로 방지된다.
         */
        Column(
            modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)

            /*
                CompositionLocalProvider
                - composition 트리를 통해 암시적으로 데이터를 전달할 수 있다.
                예제는 ContentAlpha.medium - 중간 불투명도에 액세스하고 있다.
             */
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LayoutInComposeTheme {
        PhotographerCard()
    }
}
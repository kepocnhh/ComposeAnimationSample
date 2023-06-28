package test.compose.animation

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

internal class MainActivity : AppCompatActivity() {
    @Composable
    private fun Block(
        text: String,
        modifier: Modifier = Modifier,
        enter: EnterTransition = fadeIn() + expandIn(),
        exit: ExitTransition = shrinkOut() + fadeOut(),
    ) {
        val visibility = remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {
            AnimatedVisibility(
                visible = visibility.value,
                modifier = modifier,
                enter = enter,
                exit = exit,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(Color.Red),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(Color.Green),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(Color.Blue),
                    )
                }
            }
        }
        BasicText(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { visibility.value = !visibility.value }
                .wrapContentHeight(),
            text = text,
            style = TextStyle(textAlign = TextAlign.Center),
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
            ) {
                BackHandler {
                    finish()
                }
                LazyColumn(Modifier.fillMaxWidth()) {
                    item {
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(Color.Gray))
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Black))
                    }
                    item {
                        Block(text = "default")
                    }
                    item {
                        Block(
                            text = "slide in/out",
                            enter = slideInHorizontally(),
                            exit = slideOutHorizontally(),
                        )
                    }
                    item {
                        Block(
                            text = "slide >>",
                            enter = slideInHorizontally(initialOffsetX = { -it }),
                            exit = slideOutHorizontally(targetOffsetX = { it }),
                        )
                    }
                    item {
                        Block(
                            text = "slide ><",
                            enter = slideInHorizontally(initialOffsetX = { -it }),
                            exit = slideOutHorizontally(targetOffsetX = { -it }),
                        )
                    }
                    item {
                        Block(
                            text = "fade in/out",
                            enter = fadeIn(),
                            exit = fadeOut(),
                        )
                    }
                    item {
                        Block(
                            text = "expand in shrink out",
                            enter = expandIn(),
                            exit = shrinkOut(),
                        )
                    }
                    item {
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(Color.Gray))
                    }
                }
            }
        }
    }
}

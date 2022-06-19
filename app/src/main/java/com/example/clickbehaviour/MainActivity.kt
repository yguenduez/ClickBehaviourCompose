package com.example.clickbehaviour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.clickbehaviour.ui.theme.ClickBehaviourTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClickBehaviourTheme {
                // A surface container using the 'background' color from the theme
                ClickableApp()
            }
        }
    }
}

@Composable
fun TextWithImage(modifier: Modifier) {
    var result by remember { mutableStateOf(0) }

    val image_index = result % 4
    val imageIndexWithText: Pair<Int, Int> = when(image_index) {
        0 -> Pair(R.drawable.lemon_tree, R.string.lemon_tree)
        1 -> Pair(R.drawable.lemon_squeeze, R.string.squeeze_lemon)
        2 -> Pair(R.drawable.lemon_drink, R.string.tap_lemon)
        3 -> Pair(R.drawable.lemon_restart, R.string.tap_glass)
        else -> Pair(R.drawable.lemon_tree, R.string.lemon_tree)
    }
    
    val image =  painterResource(id = imageIndexWithText.first)
    val imageText = stringResource(id = imageIndexWithText.second)

    val maybeIncrementResult = {
        val currently_squezing_lemon = image_index == 1
        var increment_result = true
        if (currently_squezing_lemon){
            val one_out_of_six = (1..6).random() == 1
            if (!one_out_of_six){
                increment_result = false
            }
        }

        if (increment_result){
            result ++
        }
    }

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = imageText, textAlign = TextAlign.Center, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = image,
            contentDescription = image_index.toString(),
            modifier = Modifier
                .wrapContentSize().border(width = 4.dp, color = Color(red = 105, green= 205, blue = 216), shape = RoundedCornerShape(8.dp))
                .clickable {
                    maybeIncrementResult()
                }
        )
    }
}

@Composable
fun ClickableApp(){
    TextWithImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClickBehaviourTheme {
        ClickableApp()
    }
}
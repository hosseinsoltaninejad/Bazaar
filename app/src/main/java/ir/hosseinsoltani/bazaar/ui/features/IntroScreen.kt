package ir.hosseinsoltani.bazaar.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import ir.hosseinsoltani.bazaar.R
import ir.hosseinsoltani.bazaar.ui.theme.BackgroundMain
import ir.hosseinsoltani.bazaar.ui.theme.Blue
import ir.hosseinsoltani.bazaar.ui.theme.MainAppTheme
import ir.hosseinsoltani.bazaar.util.MyScreens


@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    MainAppTheme {
        Surface(
            color = BackgroundMain,
            modifier = Modifier.fillMaxSize()
        ) {
            IntroScreen()
        }
    }
}

@Composable
fun IntroScreen() {
    val uiController = rememberSystemUiController()
    SideEffect { uiController.setStatusBarColor(Blue) }

      val navigation = getNavController()
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.img_intro),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.78f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Button(
            modifier = Modifier.fillMaxWidth(0.7f),
            onClick = {
                navigation.navigate(MyScreens.SignUpScreen.route)
            }
        ) {
            Text(
                text = "Sign Up"
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            onClick = {
                navigation.navigate(MyScreens.SignInScreen.route)
            }
        ) {
            Text(
                text = "Sign In" ,
                color = Blue
            )
        }
    }

}
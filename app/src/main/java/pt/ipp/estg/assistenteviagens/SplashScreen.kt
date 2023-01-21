package pt.ipp.estg.assistenteviagens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.ipp.estg.assistenteviagens.ui.theme.AssistenteViagensTheme

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistenteViagensTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val mContext = LocalContext.current
                    InitialSplash()
                    Handler().postDelayed({
                        val intent = Intent(mContext, MainActivity::class.java)
                        mContext.startActivity(intent)
                        finish()
                    }, 1500)
                }
            }
        }
    }
}
@Composable
fun InitialSplash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(250.dp)
                .clip(RoundedCornerShape(percent = 10)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "LogoApp",
        )
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    AssistenteViagensTheme{
        InitialSplash()
    }
}

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import ot.base64.Base64View
import ot.jwt.JwtView
import ru.impression.compose_jb_routing.Router
import ru.impression.compose_jb_routing.initRouting
import ru.impression.compose_jb_routing.routing

object MyAppRoute {
    const val JWT = "/jwt"
    const val BASE_64 = "/base64"
}

@Composable
fun mainView() {
    initRouting(MyAppRoute.JWT)
    Surface {
        Row {
            Box(modifier = Modifier.width(Dp(300.0F)), contentAlignment = Alignment.Center) {
                leftNav()
            }
            Router {
                route(MyAppRoute.JWT, exact = true) { JwtView() }
                route(MyAppRoute.BASE_64, exact = true) { Base64View() }
            }
        }
    }
}

@Composable
fun leftNav() {
    Column {
        Scaffold(topBar = {
            TopAppBar(title = { Text("Left Nav") })
        }, content = {
            Column {
                Text("JWT", modifier = Modifier.clickable { routing.push(MyAppRoute.JWT) })
                Text("Base64", modifier = Modifier.clickable { routing.push(MyAppRoute.BASE_64) })
            }
        })
    }
}

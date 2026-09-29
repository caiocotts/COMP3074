package ca.gbc.comp3074.lab2

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.gbc.comp3074.lab2.ui.theme.DepressingBlue
import ca.gbc.comp3074.lab2.ui.theme.FunnyYellow
import ca.gbc.comp3074.lab2.ui.theme.Lab2Theme
import ca.gbc.comp3074.lab2.ui.theme.MeanieWhite
import ca.gbc.comp3074.lab2.ui.theme.SalespersonRed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val transparent = 0
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(transparent),
            navigationBarStyle = SystemBarStyle.dark(transparent)
        )
        super.onCreate(savedInstanceState)

        setContent {
            val count = remember { mutableIntStateOf(0) }

            Lab2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(70.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ena),
                            contentDescription = null,
                            modifier = Modifier.clip(RoundedCornerShape(30.dp))
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = count.intValue.toString(), fontSize = 30.sp)
                        Controls(count, LocalContext.current)
                    }
                }
            }
        }
    }
}

@Composable
fun Controls(count: MutableIntState, context: Context) {
    val step = remember { mutableIntStateOf(1) }
    val stepToggle = remember { mutableStateOf(true) }
    val buttonWidth = 150.dp
    val fontSize = 30.sp

    Column(modifier = Modifier.width(350.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    count.intValue -= step.intValue
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = DepressingBlue,
                    contentColor = FunnyYellow
                ),
                modifier = Modifier.width(buttonWidth)
            ) {
                Text(
                    "-",
                    fontSize = fontSize
                )
            }
            Button(
                onClick = {
                    count.intValue += step.intValue
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = FunnyYellow,
                    contentColor = DepressingBlue
                ),
                modifier = Modifier.width(buttonWidth)
            ) {
                Text(
                    "+",
                    fontSize = fontSize
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    count.intValue = 0
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SalespersonRed,
                    contentColor = MeanieWhite
                ),
                modifier = Modifier.width(buttonWidth)
            ) {
                Text(
                    "reset",
                    fontSize = fontSize
                )
            }
            Button(
                onClick = {
                    if (stepToggle.value) {
                        step.intValue = 2
                    } else {
                        step.intValue = 1
                    }
                    Toast.makeText(context, "step is set to ${step.intValue}", Toast.LENGTH_SHORT)
                        .show()
                    stepToggle.value = !stepToggle.value
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MeanieWhite,
                    contentColor = SalespersonRed
                ),
                modifier = Modifier.width(buttonWidth)
            ) {
                Text(
                    "step",
                    fontSize = fontSize
                )
            }
        }
    }

}

package com.example.jetpackcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {

    var authentication: FirebaseAuth? = null
    var context: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authentication = FirebaseAuth.getInstance()

        val currentUser = FirebaseAuth.getInstance().currentUser
        setContent {

            /*if (currentUser != null) {
                HomeScreen()
            } else {
                LoginScreen(authentication, this)
            }*/
            AppRoot(authentication, context)
        }
    }
}

@Composable
fun AppRoot(authentication: FirebaseAuth?, context: MainActivity?) {

    var showSplash by remember { mutableStateOf(true) }
    var currentUser by remember { mutableStateOf(authentication?.currentUser) }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)

        // Check Firebase session after splash
        currentUser = authentication?.currentUser

        showSplash = false
    }
    when {
        showSplash -> {
            SplashScreen()
        }

        currentUser != null -> {
            HomeScreen()
        }

        else -> {
            LoginScreen(
                authentication = authentication,
                context = context,
                onLoginSuccess = {
                    currentUser = authentication?.currentUser
                }
            )
        }

    }

}


@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Color(0xFF1565C0)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "JC",
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Jetpack Compose",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF1565C0)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Welcome to our application",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(35.dp))

        CircularProgressIndicator(
            modifier = Modifier.size(35.dp),
            color = Color(0xFF1565C0),
            strokeWidth = 3.dp
        )
    }
}

@Composable
fun LoginScreen(
    authentication: FirebaseAuth?,
    onLoginSuccess: () -> Unit,
    context: MainActivity?
) {

    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            MyToolBar()
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Login Icon
            /*Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Login",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(shape = RoundedCornerShape(50.dp,), width = 1.dp, color = Color.Red)
                    .background(color = Color.Cyan),
                tint = Color.Red
            )*/

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Login",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(color = Color.Cyan)
                    .border(width = 1.dp, Color.Red, CircleShape),
                tint = Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Login Title
            Text(
                text = "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(35.dp))

            // Username
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Username")
                },
                placeholder = {
                    Text("Enter username")
                },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Username"
                    )
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password
            OutlinedTextField(
                value = password,
                isError = false,
                onValueChange = {
                    password = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Password")
                },
                placeholder = {
                    Text("Enter password")
                },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password"
                    )
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            // Login Button
            Button(
                onClick = {
                    val data = UserModel()
                    data.name = name
                    data.password = password

                    /* authentication?.createUserWithEmailAndPassword(name, password)
                         ?.addOnCompleteListener { task ->
                             if (task.isSuccessful) {
                                 Log.d("Success", "Login Success")
                                 homeScreen(context)
                             }

                         }*/


                    authentication?.signInWithEmailAndPassword(name, password)?.addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                onLoginSuccess()
                            } else {
                                Toast.makeText(
                                    context,
                                    task.exception?.localizedMessage ?: "Login failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    Log.d("DONE", data.name.toString())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Login",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Login"
                    )
                }
            }
        }
    }
}

fun homeScreen(context: Context) {
    val intent = Intent(context, HomeScreen::class.java)
    context.startActivity(intent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolBar() {

    TopAppBar(
        title = {
            Text(
                text = "Jetpack Compose App"
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun HomeScreenPreview() {
    //LoginScreen(null, context = MainActivity())
}
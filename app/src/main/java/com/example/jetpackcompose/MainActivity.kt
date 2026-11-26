package com.example.jetpackcompose

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()

        setContent {
            AppRoot(auth)
        }
    }
}

/* ---------------------------------------------------------------
   Root: splash -> (valid user ? Home : Sign up / Login)
   --------------------------------------------------------------- */
@Composable
fun AppRoot(auth: FirebaseAuth) {

    var showSplash by remember { mutableStateOf(true) }
    var currentUser by remember { mutableStateOf(auth.currentUser) }

    LaunchedEffect(Unit) {
        delay(2000.milliseconds)  // Here whenever the new ui or composable is created first this effect is called then only it decides which screen need to go
        // Firebase keeps the session on the device, so this is the saved user
        currentUser = auth.currentUser
        showSplash = false
    }

    when {
        showSplash -> SplashScreen()

        currentUser != null -> HomeScreen(
            email = currentUser?.email ?: "",
            onLogout = {
                auth.signOut()
                currentUser = null
            }
        )

        else -> AuthScreen(
            auth = auth,
            onSuccess = { currentUser = auth.currentUser }
        )
    }
}

/* ---------------------------------------------------------------
   Splash
   --------------------------------------------------------------- */
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

/* ---------------------------------------------------------------
   Sign up / Login (one screen, toggled)
   --------------------------------------------------------------- */
@Composable
fun AuthScreen(
    auth: FirebaseAuth,
    onSuccess: () -> Unit
) {
    val context = LocalContext.current   // never null, fixes the Toast crash

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSignUp by remember { mutableStateOf(true) }
    var loading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { MyToolBar() }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Cyan)
                    .border(1.dp, Color.Red, CircleShape),
                tint = Color.Red
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (isSignUp) "Create Account" else "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(35.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") },
                placeholder = { Text("Enter email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                placeholder = { Text("Enter password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(), //******* instead of text
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null)
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            // Submit button
            Button(
                onClick = {
                    val mail = email.trim()

                    if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                        Toast.makeText(context, "Enter a valid email", Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if (password.length < 6) {
                        Toast.makeText(
                            context,
                            "Password must be at least 6 characters",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }

                    loading = true

                    val task = if (isSignUp) {
                        auth.createUserWithEmailAndPassword(mail, password)
                    } else {
                        auth.signInWithEmailAndPassword(mail, password)
                    }

                    task.addOnCompleteListener { result ->
                        loading = false
                        if (result.isSuccessful) {
                            onSuccess()   // AppRoot switches to HomeScreen
                        } else {
                            Toast.makeText(
                                context,
                                result.exception?.localizedMessage ?: "Something went wrong",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                },
                enabled = !loading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = if (isSignUp) "Sign Up" else "Login",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }
            }

            TextButton(onClick = { isSignUp = !isSignUp }) {
                Text(
                    if (isSignUp) "Already have an account? Login"
                    else "New user? Create account"
                )
            }
        }
    }
}

/* ---------------------------------------------------------------
   Home (main screen for a valid user)
   If you already have your own HomeScreen, delete this one and
   add the email / onLogout parameters to yours.
   --------------------------------------------------------------- */
@Composable
fun HomeScreen(
    email: String,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = { MyToolBar() }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Welcome",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = email,
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedButton(
                onClick = onLogout,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Logout")
            }
        }
    }
}

/* ---------------------------------------------------------------
   Toolbar
   --------------------------------------------------------------- */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolBar() {

    TopAppBar(
        title = {
            Text(text = "Jetpack Compose App")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

/* ---------------------------------------------------------------
   Previews
   --------------------------------------------------------------- */
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashPreview() {
    SplashScreen()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(email = "user@example.com", onLogout = {})
}


/*
State change: onSuccess is the lambda you passed from AppRoot:
kotlin
onSuccess = { currentUser = auth.currentUser }

After a successful sign-up, Firebase has already signed the new user in, so auth.currentUser is no longer null. Assigning it to currentUser changes a mutableStateOf value.

Recomposition: Compose sees that currentUser changed, so AppRoot recomposes. The when block is evaluated again:
kotlin
showSplash -> ...                 // false now, skipped
currentUser != null -> HomeScreen // true, shown

AuthScreen leaves the screen and HomeScreen appears.



@Composable
fun HomeScreen(context: MainActivity?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

   Spacer(modifier = Modifier.height(14.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    Toast.makeText(context, "Submitted", Toast.LENGTH_LONG).show()
                },
                enabled = true,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                border = BorderStroke(1.dp, SolidColor(Color(0XFFD2D2D2)))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Submit",
                        textAlign = TextAlign.Center,
//                color = Color.White,
                        fontSize = 16.sp,
                        //fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(10.dp)
                    )
                    if(showDone){
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Login"
                        )
                    }
                }

            }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewScreen() {
    val context = LocalContext.current
    HomeScreen(context as MainActivity?)
}*/

package com.example.greenflag

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenflag.ui.theme.GreenFlagTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreenFlagTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .safeContentPadding()
                ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier
                            .background(Color.Black)
                            .fillMaxSize()
                            .padding(innerPadding)


                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier) {

    var showCreateAccountScreen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        if (showCreateAccountScreen) {
            Box(modifier = Modifier.fillMaxSize()) {
                CreateAccountScreen(onGoBack = { showCreateAccountScreen = false })
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo_green_flag),
                contentDescription = "Tick",
                modifier = Modifier
                    .size(400.dp)

            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            Column(
                modifier = Modifier.width(340.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Text(
                        "Greenflag customers can \ncreate an account to access:",
                        color = Color.White,
                        fontSize = 24.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.tick),
                        contentDescription = "Tick"
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        "Car health updates", color = Color.White,
                        fontSize = 24.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.tick),
                        contentDescription = "Tick"
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        "Request resque online", color = Color.White,
                        fontSize = 24.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.tick),
                        contentDescription = "Tick"
                    )
                    Spacer(modifier = Modifier.size(20.dp))

                    Text(
                        "Policy information", color = Color.White,
                        fontSize = 24.sp
                    )
                }
            }


        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(400.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        showCreateAccountScreen = true
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.gradient_button_background),
                    contentDescription = "Create an account",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    "Create an account",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(onGoBack: () -> Unit) {
    var showLoggedInScreen by remember { mutableStateOf(false) }

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatedPassword by remember { mutableStateOf("") }

    if (showLoggedInScreen) {
        Box(modifier = Modifier.fillMaxSize()) {
            UserScreen(onGoBack = {
                showLoggedInScreen = false
                onGoBack()
            }, userEmail = email)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Scaffold(
            modifier = Modifier.background(Color.Black),
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    navigationIcon = {
                        IconButton(onClick = onGoBack, modifier = Modifier) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )

                        }
                    },
                    title = { Text("Create an account", color = Color.Red) },
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .background(Color.Black)
                ) {
                    Row() {
                        Column() {
                            Row() {
                                Text("Email Address", color = Color.White)
                            }
                            Row() {
                                TextField(
                                    value = email,
                                    onValueChange = { newText ->
                                        email = newText
                                    }
                                )
                            }
                        }
                    }
                    Row() {
                        Column() {
                            Row() {
                                Text("Create password", color = Color.White)
                            }
                            Row() {
                                TextField(
                                    value = password,
                                    onValueChange = { newText -> password = newText }
                                )
                            }
                        }
                    }
                    Row() {
                        Column() {
                            Row() {
                                Text("Repeat password", color = Color.White)
                            }
                            Row() {
                                TextField(
                                    value = repeatedPassword,
                                    onValueChange = { newText -> repeatedPassword = newText }
                                )
                            }
                        }
                    }
                    Row() {
                        Text("Your password should have a minimum of 8 \ncharacters and contain at least one number, one uppercase and one lower case letter. You can use special characters.")
                    }
                    Row() {
                        Box(
                            modifier = Modifier
                                .width(400.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable {
                                    if (email.contains("@") && email.contains(".com")) {
                                        if (validatePassword(password)) {
                                            if (password == repeatedPassword) {

                                                Toast.makeText(
                                                    context, "Your account has been created",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                                showLoggedInScreen = true
                                                onGoBack()

                                            } else {
                                                Toast.makeText(
                                                    context, "Passwords do not match",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }

                                        } else {
                                            Toast.makeText(
                                                context, "Password is not valid",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                    } else {
                                        Toast.makeText(
                                            context, "Email is in an incorrect format",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }


                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.gradient_button_background),
                                contentDescription = "Create an account",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                "Next",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }


                }
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(onGoBack: () -> Unit, userEmail: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Scaffold(
            modifier = Modifier.background(Color.Black),
            topBar = {
                TopAppBar(
                    modifier = Modifier,
                    navigationIcon = {
                        IconButton(onClick = onGoBack, modifier = Modifier) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )

                        }
                    },
                    title = { Text("Welcome $userEmail", color = Color.Red) },
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .background(Color.Black)
                ) {
                    Row() {
                        Column() {
                            Text("This is the logged in page, you just created an account!")
                        }
                    }
                    Row() {
                        Button(onClick = onGoBack) { }
                    }
                }
            })
    }
}

fun validatePassword(password: String): Boolean {
    if (password.length < 8) return false
    if (password.filter { it.isDigit() }.firstOrNull() == null) return false
    if (password.filter { it.isLetter() }.filter { it.isUpperCase() }
            .firstOrNull() == null) return false
    if (password.filter { it.isLetter() }.filter { it.isLowerCase() }
            .firstOrNull() == null) return false
    return true
}




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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
                    Box() {
                        var currentScreen by remember { mutableStateOf("HomeScreen") }
                        when (currentScreen) {

                            "HomeScreen" -> HomeScreen(
                                modifier = Modifier
                                    .background(Color.Black)
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                onCreateAccountNavigation = {
                                    currentScreen = "CreateAccountScreen"
                                }
                            )

                            "CreateAccountScreen" -> (
                                    CreateAccountScreen(
                                        onGoBack = { currentScreen = "HomeScreen" },
                                        onAccountCreated = { currentScreen = "UserScreen" })

                                    )

                            "UserScreen" -> (
                                    UserScreen(
                                        onLogOut = { currentScreen = "HomeScreen" }
                                    )
                                    )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier, onCreateAccountNavigation: () -> Unit) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {


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
                        onCreateAccountNavigation()
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
fun CreateAccountScreen(onGoBack: () -> Unit, onAccountCreated: () -> Unit) {

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var emailIsFocused by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var passwordIsFocused by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var repeatedPassword by remember { mutableStateOf("") }
    var repeatedPasswordIsFocused by remember { mutableStateOf(false) }
    var isRepeatedPasswordVisible by remember { mutableStateOf(false) }

    val nextButtonEnabled =
        (validatePassword(password) && validateEmail(email) && (password == repeatedPassword))

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
                        IconButton(
                            onClick = onGoBack,
                            modifier = Modifier
                                .background(Color.Black)
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Green,
                            )

                        }
                    },
                    title = { Text("Create an account", color = Color.White, fontSize = 24.sp) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = Color.Green
                    )
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .background(Color.Black)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    ) {
                        Column(
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                            ) {
                                CustomText("Email Address:")
                            }
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                            ) {
                                OutlinedTextField(
                                    value = email,

                                    onValueChange = { newText ->
                                        email = newText
                                    },
                                    isError = (!validateEmail(email) && !emailIsFocused && email.length > 1),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        focusedIndicatorColor = colorResource(R.color.teal_700),
                                        unfocusedIndicatorColor = if (validateEmail(email)) colorResource(
                                            R.color.teal_700
                                        ) else Color.Gray,
                                        errorIndicatorColor = Color.Red,
                                    ),
                                    trailingIcon = {
                                        if (validateEmail(email)) {
                                            Icon(
                                                Icons.Default.Done,
                                                contentDescription = "Email is correct",
                                                tint = colorResource(R.color.teal_700),
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onFocusChanged() { focusState ->
                                            emailIsFocused = focusState.isFocused
                                        },
                                    supportingText = {
                                        if (!validateEmail(email) && email.length > 1 && !emailIsFocused) {
                                            Text("Email is incorrect", color = Color.Red)
                                        }
                                    }
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .fillMaxWidth()
                    ) {
                        Column() {
                            Row(modifier = Modifier.padding(vertical = 5.dp)) {
                                CustomText("Create password")
                            }
                            Row(modifier = Modifier.padding(vertical = 5.dp)) {
                                OutlinedTextField(
                                    value = password,
                                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                    onValueChange = { newText: String -> password = newText },
                                    isError = (!validatePassword(password) && !passwordIsFocused && password.length > 1),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        focusedIndicatorColor = colorResource(R.color.teal_700),
                                        unfocusedIndicatorColor = if (validatePassword(password)) colorResource(
                                            R.color.teal_700
                                        ) else Color.Gray,
                                        errorIndicatorColor = Color.Red,

                                        ),
                                    trailingIcon = {
                                        val icon =
                                            if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                                        IconButton(onClick = {
                                            isPasswordVisible = !isPasswordVisible
                                        }) {
                                            if (validatePassword(password) && !passwordIsFocused) {
                                                Icon(
                                                    Icons.Default.Done,
                                                    contentDescription = "Email is correct",
                                                    tint = colorResource(R.color.teal_700),
                                                    modifier = Modifier.size(24.dp)
                                                )
                                            } else {
                                                Icon(
                                                    icon,
                                                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                                                )
                                            }
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onFocusChanged() { focusState ->
                                            passwordIsFocused = focusState.isFocused
                                        },
                                    supportingText = {
                                        if (!validatePassword(password) && password.length > 1 && !passwordIsFocused) {
                                            Text("Error with password", color = Color.Red)
                                        }
                                    }

                                )
                            }
                        }
                    }
                    Row(modifier = Modifier.padding(vertical = 5.dp)) {
                        Column() {
                            Row(modifier = Modifier.padding(vertical = 5.dp)) {
                                CustomText("Repeat password")
                            }
                            Row(modifier = Modifier.padding(vertical = 5.dp)) {
                                OutlinedTextField(
                                    value = repeatedPassword,
                                    onValueChange = { newText -> repeatedPassword = newText },
                                    visualTransformation = if (isRepeatedPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                    isError = (!validatePassword(repeatedPassword) && !repeatedPasswordIsFocused && repeatedPassword.length > 1),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        focusedIndicatorColor = colorResource(R.color.teal_700),
                                        unfocusedIndicatorColor = if (!validatePassword(
                                                repeatedPassword
                                            ) && !repeatedPasswordIsFocused && (password == repeatedPassword) && repeatedPassword.length > 1

                                        ) colorResource(
                                            R.color.teal_700
                                        ) else Color.Gray,
                                        errorIndicatorColor = Color.Red,

                                        ),
                                    trailingIcon = {
                                        val icon =
                                            if (isRepeatedPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                                        IconButton(onClick = {
                                            isRepeatedPasswordVisible = !isRepeatedPasswordVisible
                                        }) {

                                            if (validatePassword(repeatedPassword) && !repeatedPasswordIsFocused && (password == repeatedPassword) && repeatedPassword.length > 1) {
                                                Icon(
                                                    Icons.Default.Done,
                                                    contentDescription = "Repeated password is correct",
                                                    tint = colorResource(R.color.teal_700),
                                                    modifier = Modifier.size(24.dp)
                                                )
                                            } else {
                                                Icon(
                                                    icon,
                                                    contentDescription = if (isRepeatedPasswordVisible) "Hide repeated password" else "Show repeated password"
                                                )
                                            }

                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onFocusChanged() { focusState ->
                                            repeatedPasswordIsFocused = focusState.isFocused
                                        },
                                    supportingText = {
                                        if (!repeatedPasswordIsFocused && (repeatedPassword != password) && repeatedPassword.isNotEmpty()) {
                                            Text("Passwords don't match", color = Color.Red)
                                        }
                                    }
                                )
                            }
                        }
                    }
                    Row(modifier = Modifier.padding(vertical = 5.dp)) {
                        Text(
                            "Your password should have a minimum of 8 \ncharacters and contain at least one number, one uppercase and one lower case letter. You can use special characters.",
                            color = Color.White
                        )
                    }
                    Row(modifier = Modifier.padding(vertical = 5.dp)) {

                        Box(
                            modifier = Modifier
                                .width(400.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable(enabled = nextButtonEnabled) {
                                    if (validatePassword(password)) {
                                        if (password == repeatedPassword) {

                                            Toast.makeText(
                                                context, "Your account has been created",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            onAccountCreated()

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


                                },
                            contentAlignment = Alignment.Center,

                            ) {
                            Image(
                                painter = painterResource(
                                    R.drawable.gradient_button_background,

                                    ),
                                contentDescription = "Create an account",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                colorFilter = if (nextButtonEnabled) null else ColorFilter.tint(
                                    Color.Gray
                                )

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
fun UserScreen(onLogOut: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                "This is the logged in page, you just created an account!",
                color = Color.White,
                fontSize = 18.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Button(onClick = onLogOut, modifier = Modifier.padding(32.dp)) {
                Text("LOG OUT", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun CustomText(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 27.sp,
    )
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

fun validateEmail(email: String): Boolean {
    return email.contains("@") && email.contains(".com")
}



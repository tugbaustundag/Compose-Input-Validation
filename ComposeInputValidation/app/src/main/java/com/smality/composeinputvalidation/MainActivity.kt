package com.smality.composeinputvalidation
import androidx.compose.foundation.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.*
import com.smality.composeinputvalidation.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeInputValidationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),color = yellow
                ) {
                    ValidateEmail()
                }
            }
        }
    }
}
//Email Input oluşturma
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(email: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = { onEmailChange(it) },
        label = { Text("Email", color = golden) },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = golden, unfocusedBorderColor = gray)
    )
}
//Password Input oluşturma
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassTextField(password: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().absolutePadding(0.dp, 10.dp, 0.dp, 0.dp),
        value = password,
        onValueChange = { onEmailChange(it) },
        label = { Text("Password", color = golden) },
        singleLine = true,
        //Yazılan karakterler . şeklinde gözükmesi için özellik atadık
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = golden, unfocusedBorderColor = gray)

    )
}
//Email Validation
fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}
//Tasarımı ve Input Validation Oluşturma
@Composable
fun ValidateEmail() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    Column(Modifier.fillMaxWidth().absolutePadding(50.dp, 0.dp, 50.dp, 0.dp),horizontalAlignment = Alignment.CenterHorizontally) {
        //Logoyu tasarıma ekleyelim.
        Image(
            painter = painterResource(id=R.drawable.umay_logo),
            contentDescription = "logo",
            modifier = Modifier.size(260.dp)
        )

        EmailTextField(email = email, onEmailChange = { email = it })
        //Email input uyarılarını yazdılarılım
        Text(text = errorText, color = darkRed)

        PassTextField(password = password, onEmailChange = { password = it })

        Button(
            onClick = {
                //Email input kontrolü
                if (email.isNotEmpty()) {

                    if (isValidEmail(email)) {
                        errorText="Email adresi başarılı."
                    } else {
                        errorText="Email geçerli değil."
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF804C4D)),
            modifier = Modifier.width(150.dp).absolutePadding(0.dp, 10.dp, 0.dp, 0.dp),
        ) {
            Text(text = "LOGIN",color = Color.White, fontSize = 16.sp)
        }
    }
}
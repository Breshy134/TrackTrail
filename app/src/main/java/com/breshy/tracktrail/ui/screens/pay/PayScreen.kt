package com.breshy.tracktrail.ui.screens.pay

import android.content.Intent
import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.breshy.tracktrail.navigation.ROUT_BOOKINGS
import com.breshy.tracktrail.navigation.ROUT_PAY
import com.breshy.tracktrail.ui.theme.brown
import com.breshy.tracktrail.ui.theme.green
import com.breshy.tracktrail.ui.theme.newbrown
import com.breshy.tracktrail.ui.theme.newsky

@Composable
fun PayScreen(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp)
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                //Variables
                var enterphone by remember { mutableStateOf("") }
                var enteramount by remember { mutableStateOf("") }

                //entersource
                TextField(
                    value = enterphone,
                    onValueChange = {enterphone = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "", tint = newbrown) },
                    label = { Text(text = "Enter Phone Number") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = newbrown,
                        focusedBorderColor = brown
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

                )
                //end of entersource

                Spacer(modifier = Modifier.height(10.dp))

                //enterdestination
                TextField(
                    value = enteramount,
                    onValueChange = {enteramount = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    leadingIcon = { Icon(imageVector = Icons.Default.Place, contentDescription = "", tint = newbrown) },
                    label = { Text(text = "Enter Amount") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = newbrown,
                        focusedBorderColor = brown
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

                )
                //end of enterdestination

                Spacer(modifier = Modifier.height(20.dp))

                //Button

                Button(
                    onClick = { ROUT_BOOKINGS },
                    colors = ButtonDefaults.buttonColors(green),
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = "Submit",
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                //End of Button

            }
    }
}


@Preview(showBackground = true)
@Composable
fun PayScreenPreview(){
    PayScreen(rememberNavController())
}
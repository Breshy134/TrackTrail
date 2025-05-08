package com.breshy.tracktrail.ui.screens.bus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.breshy.tracktrail.R
import com.breshy.tracktrail.navigation.ROUT_BOOKINGS
import com.breshy.tracktrail.navigation.ROUT_DASHBOARD
import com.breshy.tracktrail.navigation.ROUT_PAY
import com.breshy.tracktrail.ui.theme.brown
import com.breshy.tracktrail.ui.theme.green
import com.breshy.tracktrail.ui.theme.newBlue
import com.breshy.tracktrail.ui.theme.newbrown
import com.breshy.tracktrail.ui.theme.newgreen
import com.breshy.tracktrail.ui.theme.newgrey
import com.breshy.tracktrail.ui.theme.neworange
import com.breshy.tracktrail.ui.theme.newsky

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScreen(navController: NavHostController) {
    //Scaffold

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(

        //BottomBar
        bottomBar = {
            NavigationBar(
                containerColor = green
            ){
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0
                        navController.navigate(ROUT_DASHBOARD)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Bookings") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1
                        navController.navigate(ROUT_BOOKINGS)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2
                        navController.navigate("")
                    }
                )

            }
        },

        //Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {

                TopAppBar(
                    title = { Text(text = "TrackTrail") },
                    navigationIcon = {
                        IconButton(onClick = { ROUT_DASHBOARD}) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                //Row
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .horizontalScroll(rememberScrollState())
                ) {

                    //Card1
                    Card(
                        modifier = Modifier
                            .width(350.dp)
                            .height(250.dp)
                    ) {
                        Column() {

                            Spacer(modifier = Modifier.height(30.dp))

                            //Variables
                            var entersource by remember { mutableStateOf("") }
                            var enterdestination by remember { mutableStateOf("") }

                            //entersource
                            TextField(
                                value = entersource,
                                onValueChange = {entersource = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp),
                                leadingIcon = { Icon(imageVector = Icons.Default.Place, contentDescription = "", tint = newbrown) },
                                label = { Text(text = "Enter Source") },
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = newbrown,
                                    focusedBorderColor = brown
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

                            )
                            //end of enterdestination

                            Spacer(modifier = Modifier.height(10.dp))

                            //entersource
                            TextField(
                                value = enterdestination,
                                onValueChange = {enterdestination = it},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp),
                                leadingIcon = { Icon(imageVector = Icons.Default.Place, contentDescription = "", tint = newbrown) },
                                label = { Text(text = "Enter Destination") },
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
                                onClick = { ROUT_BOOKINGS},
                                colors = ButtonDefaults.buttonColors(green),
                                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                            ) {
                                Text(
                                    text = "Book Now",
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }

                            //End of Button

                        }
                    }
                }
                //End of Card1
                Spacer(modifier = Modifier.width(20.dp))

                //Card2
                Card(
                    modifier = Modifier
                        .width(370.dp)
                        .height(400.dp)
                        .padding(start = 20.dp, top = 20.dp)
                ) {

                    Spacer(modifier = Modifier.height(23.dp))
                    Column() {
                        Text(
                            text = "Why Book From TrackTrail",
                            fontSize = 25.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, top = 20.dp),
                        )

                        Spacer(modifier = Modifier.height(45.dp))

                        //Row
                        Row (
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .horizontalScroll(rememberScrollState())
                        ){
                            //Card1
                            Card (
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(200.dp)
                            ){
                                Column (
                                    modifier = Modifier.fillMaxSize().background(newgrey),
                                    verticalArrangement = Arrangement.Center
                                ){
                                    Card (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .width(150.dp)
                                            .height(150.dp)
                                            .padding(start = 20.dp,end = 20.dp)


                                    ){
                                        Spacer(modifier = Modifier.height(15.dp))
                                        Image(
                                            painter = painterResource(R.drawable.bus),
                                            contentDescription = "",
                                            modifier = Modifier.size(100.dp).align(alignment = Alignment.CenterHorizontally)
                                        )
                                        Text(
                                            text = "Live Bus Status",
                                            fontSize = 15.sp,
                                            fontStyle = FontStyle.Italic,
                                            modifier = Modifier.padding(start = 40.dp)
                                        )
                                    }
                                }
                            }
                            //End of Card1
                            Spacer(modifier = Modifier.width(20.dp))
                            //Card2
                            Card (
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(200.dp)
                            ){
                                Column (
                                    modifier = Modifier.fillMaxSize().background(newgrey), horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ){
                                    Card (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .width(150.dp)
                                            .height(150.dp)
                                            .padding(start = 20.dp,end = 20.dp)


                                    ){
                                        Spacer(modifier = Modifier.height(15.dp))
                                        Image(
                                            painter = painterResource(R.drawable.refunds),
                                            contentDescription = "",
                                            modifier = Modifier.size(100.dp).align(alignment = Alignment.CenterHorizontally)
                                        )
                                        Text(
                                            text = "Instant Refund",
                                            fontSize = 15.sp,
                                            fontStyle = FontStyle.Italic,
                                            modifier = Modifier.padding(start = 40.dp)
                                        )
                                    }
                                }
                            }
                            //End of Card2
                            Spacer(modifier = Modifier.width(20.dp))
                            //Card3
                            Card (
                                modifier = Modifier
                                    .width(250.dp)
                                    .height(200.dp)
                            ){
                                Column (
                                    modifier = Modifier.fillMaxSize().background(newgrey), horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ){
                                    Card (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .width(150.dp)
                                            .height(150.dp)
                                            .padding(start = 20.dp,end = 20.dp)


                                    ){
                                        Spacer(modifier = Modifier.height(15.dp))
                                        Image(
                                            painter = painterResource(R.drawable.time),
                                            contentDescription = "",
                                            modifier = Modifier.size(100.dp).align(alignment = Alignment.CenterHorizontally)
                                        )
                                        Text(
                                            text = "Book a ticket in 2 minutes",
                                            fontSize = 15.sp,
                                            fontStyle = FontStyle.Italic,
                                            modifier = Modifier.padding(start = 40.dp)
                                        )
                                    }
                                }
                            }
                            //End of Card3

                        }
                        //End of Row

                    }
                }
            }
            //End of Card2


        }
        //End of Row

    )

    //End of scaffold
}

@Preview(showBackground = true)
@Composable
fun BusScreenPreview(){
    BusScreen(rememberNavController())
}
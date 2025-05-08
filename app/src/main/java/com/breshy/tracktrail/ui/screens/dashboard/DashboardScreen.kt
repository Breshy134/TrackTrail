package com.breshy.tracktrail.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.breshy.tracktrail.R
import com.breshy.tracktrail.navigation.ROUT_BOOKINGS
import com.breshy.tracktrail.navigation.ROUT_BUS
import com.breshy.tracktrail.navigation.ROUT_HOME
import com.breshy.tracktrail.ui.theme.newsky

@Composable
fun DashboardScreen(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(newsky)
            .verticalScroll(rememberScrollState())
    ){
        Text(
            text = "TrackTrail",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 20.dp, top = 9.dp)
        )
        //card1
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(start = 20.dp,end = 20.dp, top = 40.dp)
                .clickable{navController.navigate(ROUT_HOME)}


        ){
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                painter = painterResource(R.drawable.maintrain),
                contentDescription = "",
                modifier = Modifier.size(200.dp).align(alignment = Alignment.CenterHorizontally)
            )
            Text(
                text = "Book Your Train Tickets Here",
                modifier = Modifier.fillMaxSize().padding(start = 50.dp, top = 10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        //end of card1
        Spacer(modifier = Modifier.height(15.dp))
        //card2
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(start = 20.dp,end = 20.dp, top = 20.dp)

        ){
            Image(
                painter = painterResource(R.drawable.mainbus),
                contentDescription = "",
                modifier = Modifier
                    .size(250.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable{navController.navigate(ROUT_BUS)}
            )
            Text(
                text = "Book Your Bus Tickets Here",
                modifier = Modifier.fillMaxSize().padding(start = 50.dp, top = 10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        //end of card2
        Spacer(modifier = Modifier.height(15.dp))
        //card3
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(start = 20.dp,end = 20.dp, top = 20.dp)

        ){
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                painter = painterResource(R.drawable.bag),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable{navController.navigate(ROUT_BOOKINGS)}
            )
            Text(
                text = "Your Bookings",
                modifier = Modifier.fillMaxSize().padding(start = 50.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        //end of card3
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview(){
    DashboardScreen(rememberNavController())
}
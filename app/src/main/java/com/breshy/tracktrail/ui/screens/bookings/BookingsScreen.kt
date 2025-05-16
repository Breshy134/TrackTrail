package com.breshy.tracktrail.ui.screens.bookings

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.*
import com.breshy.tracktrail.navigation.ROUT_BOOKINGS
import com.breshy.tracktrail.navigation.ROUT_DASHBOARD
import com.breshy.tracktrail.ui.theme.green
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Stub Entity (Replace with your actual implementation)
@Entity
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val phone: String,
    val currentCity: String,
    val destinationCity: String,
    val transportType: String,
    val amount: Double
)

// Stub DAO (Replace with actual implementation)
@Dao
interface BookingDao {
    @Insert
    suspend fun insertBooking(booking: Booking)
}

// Stub Database (Replace with actual implementation)
@Database(entities = [Booking::class], version = 1)
abstract class BookingDatabase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao
}

// ViewModel for Booking
class BookingViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        BookingDatabase::class.java, "booking-db"
    ).build()

    fun saveBooking(booking: Booking, onSaved: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            db.bookingDao().insertBooking(booking)
            onSaved()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingsScreen(navController: NavHostController, viewModel: BookingViewModel = viewModel()) {
    val context = LocalContext.current

    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var currentCity by remember { mutableStateOf("") }
    var destinationCity by remember { mutableStateOf("") }
    var transportType by remember { mutableStateOf("Bus") }
    var amount by remember { mutableStateOf(0.0) }
    var showDialog by remember { mutableStateOf(false) }

    val cities = listOf(
        "Nairobi", "Mombasa", "Kisumu", "Nakuru", "Eldoret", "Thika", "Malindi", "Garissa",
        "Kitale", "Kakamega", "Ruiru", "Kikuyu", "Naivasha", "Karuri", "Ongata Rongai", "Kitengela"
    )
    val transportOptions = listOf("Bus", "Train")
    val destinationPrices = mapOf("Nairobi" to 1000.0, "Mombasa" to 2000.0, "Kisumu" to 1500.0)

    var selectedIndex by remember { mutableStateOf(1) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = green) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(ROUT_DASHBOARD)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Bookings") },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(ROUT_BOOKINGS)
                    }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Bookings",
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(ROUT_DASHBOARD) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            // Current City Dropdown
            var currentCityExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = currentCityExpanded,
                onExpandedChange = { currentCityExpanded = !currentCityExpanded }
            ) {
                OutlinedTextField(
                    value = currentCity,
                    onValueChange = { },
                    label = { Text("Current City") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = currentCityExpanded) },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = currentCityExpanded,
                    onDismissRequest = { currentCityExpanded = false }
                ) {
                    cities.forEach { city ->
                        DropdownMenuItem(
                            text = { Text(text = city) },
                            onClick = {
                                currentCity = city
                                currentCityExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Destination City Dropdown
            var destinationCityExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = destinationCityExpanded,
                onExpandedChange = { destinationCityExpanded = !destinationCityExpanded }
            ) {
                OutlinedTextField(
                    value = destinationCity,
                    onValueChange = { },
                    label = { Text("Destination City") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = destinationCityExpanded) },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = destinationCityExpanded,
                    onDismissRequest = { destinationCityExpanded = false }
                ) {
                    cities.forEach { city ->
                        DropdownMenuItem(
                            text = { Text(text = city) },
                            onClick = {
                                destinationCity = city
                                amount = destinationPrices[city] ?: 0.0
                                destinationCityExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                transportOptions.forEach { option ->
                    Row {
                        RadioButton(
                            selected = transportType == option,
                            onClick = { transportType = option }
                        )
                        Text(option)
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (fullName.isNotBlank() && phone.length == 10 && amount > 0) {
                        val booking = Booking(
                            fullName = fullName,
                            phone = phone,
                            currentCity = currentCity,
                            destinationCity = destinationCity,
                            transportType = transportType,
                            amount = amount
                        )
                        viewModel.saveBooking(booking) {
                            showDialog = true
                            Toast.makeText(context, "Booking Successful for $phone", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Booking")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Payment Required") },
                    text = {
                        Text("Please pay KES $amount for your trip from $currentCity to $destinationCity.")
                    },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookingsScreenPreview() {
    BookingsScreen(rememberNavController())
}

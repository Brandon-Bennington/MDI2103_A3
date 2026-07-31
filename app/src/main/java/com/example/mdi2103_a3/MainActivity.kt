package com.example.mdi2103_a3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                RestaurantScreen()
            }
        }
    }
}

@Composable
fun RestaurantScreen() {
    val vipCustomers = arrayOf("Alice", "Bob", "Charlie")
    val waitingList = remember { mutableStateListOf<String>() }
    val seatedHistory = remember { mutableStateListOf<String>() }
    var customerName by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("Welcome to our Restaurant!") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Restaurant Waiting List",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                waitingList.addAll(vipCustomers)
                message = "VIP customers added"
            }
        ) {
            Text("Load VIP Customers")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (waitingList.isNotEmpty()) {
                    val customer = waitingList.removeAt(0)
                    message = "$customer is now seated"
                } else {
                    message = "No customers in line"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Seat Next Customer")
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantScreenPreview() {
    MaterialTheme {
        RestaurantScreen()
    }
}
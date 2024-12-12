package com.example.submission_vinsen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.submission_vinsen.alldata.getdata

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val createContext = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    Color(0xFF3C2B6D)
                ),
                title = { Text("Waifu List", color = Color.White) },
                actions = {
                    IconButton(onClick = {
                        val intent = Intent(createContext,Mainprofile::class.java)
                        createContext.startActivity(intent)
                    }) {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile", tint = Color.White)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.background(color = Color(0xFFE2D8F7))
                .padding(it)
                .padding(16.dp)
        ) {
            items(10) { index ->
                WibuCard(index, createContext)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun WibuCard(index: Int, createContext:Context) {
    Card(
        modifier = Modifier.shadow(2.dp, RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .padding(7.dp),
        onClick = {
            val intent = Intent(createContext,Maindetail::class.java).apply {
                putExtra("index",index)
            }
            createContext.startActivity(intent)
        }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(getdata[index].imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("${getdata[index].tittle}", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview
@Composable
private fun Wibu () {
    val dummy = rememberNavController()
    AboutScreen()
}

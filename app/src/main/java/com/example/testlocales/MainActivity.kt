package com.example.testlocales

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.testlocales.ui.theme.TestLocalesTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestLocalesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LocaleTestContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LocaleTestContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val systemLocales = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        LocaleListCompat.getAdjustedDefault()
    } else {
        LocaleListCompat.create(Locale.getDefault())
    }
    
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Locale Resolution Test",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
        
        Text(
            text = "Current Locale: ${Locale.getDefault()}",
            fontSize = 16.sp
        )
        
        Text(
            text = "System Locale Preferences:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        
        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (i in 0 until systemLocales.size()) {
                val locale = systemLocales.get(i)
                Text(
                    text = "${i + 1}. ${locale?.toString() ?: "Unknown"} (${locale?.displayName ?: "Unknown"})",
                    fontSize = 14.sp
                )
            }
        }
        
        Text(
            text = "Test String:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        
        Text(
            text = stringResource(R.string.test_string),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LocaleTestContentPreview() {
    TestLocalesTheme {
        LocaleTestContent()
    }
}
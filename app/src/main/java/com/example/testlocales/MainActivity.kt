package com.example.testlocales

import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
    val configLocales = context.resources.configuration.locales
    val localeList = LocaleList.getDefault()

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
        Text(
            text = "Locale Resolution Test",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
        Text(
            text = "In the build.gradle: \n" +
                "resourceConfigurations += listOf(\"en-rUS\", \"fr-rFR\", \"zh-rCN\")",
            fontSize = 12.sp,
        )

        Text(
            text = "Device Settings - LocaleList.getDefault():",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (i in 0 until localeList.size()) {
                val locale = localeList.get(i)
                Text(
                    text = "${i + 1}. ${locale?.toString() ?: "Unknown"} (${locale?.displayName ?: "Unknown"})",
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = "Application Context —\nthis.resources.getConfiguration().getLocales()",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = ".locales(0) is the authoritative source for what language the user " +
                "is currently seeing in your UI",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (i in 0 until configLocales.size()) {
                val locale = configLocales.get(i)
                Text(
                    text = "${i + 1}. ${locale?.toString() ?: "Unknown"} (${locale?.displayName ?: "Unknown"})",
                    fontSize = 14.sp,
                    color = if (i == 0) Color.Blue else Color.Black,
                    fontWeight = if (i == 0) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = "Locale.getDefault(): ${Locale.getDefault()}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = "R.string.test_string: ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = stringResource(R.string.test_string),
            fontSize = 18.sp,
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
package com.example.testlocales

import android.os.Bundle
import android.os.LocaleList
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
        print("TEST-APP MainActivity onCreate called with resources.configuration.locales[0]: ${resources.configuration.locales[0]} AND Locale.getDefault(): ${Locale.getDefault()}\n")

        val accessedLocaleList = this.resources.configuration.locales
        val otherLocaleList = LocaleList.getDefault()
        val primaryLocale: Locale = this.resources.configuration.locales[0]
        val locale: String = primaryLocale.displayName
        for (i in 0 until accessedLocaleList.size()) {
            val localeName = accessedLocaleList[i].displayName
            val other = otherLocaleList[i].displayName
            print("test-app MainActivity accessedLocaleList: $i) $localeName\n")
            print("test-app MainActivity otherLocaleList: $i) $other\n")
        }
        print("test-app MainActivity: onCreate called with primaryLocale: $locale\n")

        val localeConfig = this.resources.assets.locales
        println("LocaleTest LocaleConfig resources.assets.locales: ${localeConfig.joinToString()}")

        val assetManager = resources.assets
        val assetManagerLocales = assetManager.locales
        println("oz-debug: AssetManager.locales returned (${assetManagerLocales.size}): ${assetManagerLocales.joinToString()}")
        println("oz-debug: Locale.getAvailableLocales() returned (${Locale.getAvailableLocales().size}): ${Locale.getAvailableLocales().joinToString()}")
    }
}

@Composable
fun LocaleTestContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val systemLocales = LocaleListCompat.getDefault()
    val configLocales = context.resources.configuration.locales
    val localeList = LocaleList.getDefault()

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
            text = "Current Locale (Locale.getDefault()): ${Locale.getDefault()}",
            fontSize = 16.sp
        )

        Text(
            text = "System Wide - LocaleListCompat.getDefault():",
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
            text = "LocaleList.getDefault():",
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

        Text(
            text = "App specific locale - Resources.getConfiguration().getLocales():",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
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
                    color = Color.Blue
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
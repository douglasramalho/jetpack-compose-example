package com.example.jetpackcomposeexample

import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.foundation.Image
import androidx.ui.graphics.Color
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.ScaleFit
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.material.lightColorPalette
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

val primary = Color(0xFFF8F8F8)
val primaryDark = Color(0xFFECECEC)
private val themeColors = lightColorPalette(
    primary = primary,
    onPrimary = Color.Black
)

@Composable
fun MyApp(children: @Composable() () -> Unit) {
    MaterialTheme(colors = themeColors) {
        Scaffold(
            topAppBar = {
                TopAppBar(
                    title = {
                        Container(width = 65.dp) {
                            Image(
                                scaleFit = ScaleFit.FillWidth,
                                image = imageFromResource(
                                    ContextAmbient.current.resources,
                                    R.drawable.insta_logo
                                )
                            )
                        }
                    },
                    navigationIcon = {
                        Image(
                            modifier = LayoutPadding(start = 16.dp),
                            image = imageFromResource(
                                ContextAmbient.current.resources,
                                R.drawable.ic_photo
                            )
                        )
                    }
                )
            }
        ) {
            children()
        }
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    MyApp {

    }
}
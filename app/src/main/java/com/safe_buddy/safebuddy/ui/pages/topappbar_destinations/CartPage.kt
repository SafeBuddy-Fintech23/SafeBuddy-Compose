package com.safe_buddy.safebuddy.ui.pages.topappbar_destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage(navController: NavController) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
                }
            },
            title = { Text(text = "Cart") })
    }) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartPagePreview() {
    CartPage(rememberNavController())
}

@Composable
fun CartItem(
    imageUrl: String = ""
) {
    Card(
        modifier = Modifier
//            .width(200.dp)
    ) {
        Column {
            Card(
                modifier = Modifier
                    .height(200.dp)
            ) {
                Box {
                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(imageUrl)
                            .build()
                    )
                    if (painter.state is AsyncImagePainter.State.Empty ||
                        painter.state is AsyncImagePainter.State.Error
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.BrokenImage,
                            contentDescription = null,
                            Modifier.fillMaxSize()
                        )
                    }
                    if (painter.state is AsyncImagePainter.State.Success) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }
            ListItem(headlineContent = { Text(text = "Item Name Item Name") }, supportingContent = {
                Text(
                    text = "$ 54"
                )
            }, trailingContent = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Outlined.Remove, contentDescription = "remove")
                }
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    CartItem()
}
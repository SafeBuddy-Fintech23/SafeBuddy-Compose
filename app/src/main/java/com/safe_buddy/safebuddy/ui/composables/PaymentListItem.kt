package com.safe_buddy.safebuddy.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun PaymentListItem(
    price: Int = 50,
    name: String = "Item Name Item Name",
    daysLeft: Int = 25,
    isDue: Boolean = false,
) {
    ListItem(
        overlineContent = { Text(text = "$$price") },
        leadingContent = { ItemLeadingImage() },
        headlineContent = { Text(text = name) },
        supportingContent = {
            Text(
                text = "$daysLeft days left", color = if (isDue) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.primary
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PaymentListItemPreview() {
    PaymentListItem()
}

@Composable
fun ItemLeadingImage(
    imageUrl: String = "https://picsum.photos/200",
    size: Dp = 60.dp,
    description: String? = null,
) {
    Box(modifier = Modifier.size(size)) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl).crossfade(true)
                .build()
        )

        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        if (painter.state is AsyncImagePainter.State.Empty || painter.state is AsyncImagePainter.State.Error) {

            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.size(36.dp),
            )
        }

        Image(
            painter = painter,
            contentDescription = description,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = ShapeDefaults.Medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemLeadingImagePreview() {
    ItemLeadingImage()
}
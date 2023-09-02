package com.safe_buddy.safebuddy.ui.pages.bottom_nav_destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.safe_buddy.safebuddy.ui.composables.CustomCreditCard
import com.safe_buddy.safebuddy.ui.composables.PaymentListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DockedSearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            query = text,
            onQueryChange = { text = it },
            onSearch = { active = false },
            active = active,
            onActiveChange = {
                active = it
                text = ""
            },
            placeholder = { Text("Search Payment") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (active) IconButton(onClick = {
                    active = false

                }) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear")
                }
            }
        ) {
            Column {
                // TODO: Add content here
            }
        }
        CustomCreditCard()
        Spacer(modifier = Modifier.height(8.dp))

        PaymentListItem()
        PaymentListItem()
        PaymentListItem()
        PaymentListItem()
        PaymentListItem()
        PaymentListItem()

    }
}

@Preview
@Composable
fun HomePagePreview() {
    HomePage()
}
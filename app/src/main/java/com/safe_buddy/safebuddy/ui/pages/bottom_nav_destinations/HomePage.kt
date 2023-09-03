package com.safe_buddy.safebuddy.ui.pages.bottom_nav_destinations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material3.Card
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.safe_buddy.safebuddy.ui.composables.CustomCreditCard
import com.safe_buddy.safebuddy.ui.composables.PaymentListItem
import com.safe_buddy.safebuddy.ui.viewmodels.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(viewModel: HomeScreenViewModel) {
    var uiSate = viewModel.uiState.collectAsState()

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
        CustomCreditCard(
            userName = uiSate.value.userName.toString()
        )
        Spacer(modifier = Modifier.height(8.dp))
        SpendAnalysisButton {

        }
        Spacer(modifier = Modifier.height(8.dp))
        PaymentListItem(paymentProgress = 0.2f)
        PaymentListItem(paymentProgress = 0.6f)
        PaymentListItem(paymentProgress = 0.8f)

    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(viewModel = viewModel())
}

@Composable
fun SpendAnalysisButton(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.tertiary
        ),
    ) {
        ListItem(
            leadingContent = {
                Icon(
                    imageVector = Icons.Outlined.QueryStats,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            headlineContent = {
                Text(
                    text = "Spend Analysis",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            })
    }
}

@Preview(showBackground = true)
@Composable
fun SpendAnalysisButtonPreview() {
    SpendAnalysisButton({})
}
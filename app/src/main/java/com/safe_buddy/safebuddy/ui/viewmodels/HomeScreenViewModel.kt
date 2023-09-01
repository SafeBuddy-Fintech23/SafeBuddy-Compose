package com.safe_buddy.safebuddy.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.safe_buddy.safebuddy.ui.model.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserData()
    }

    private fun getUserData() {
        val currentUser = Firebase.auth.currentUser
        val photoUrl = currentUser?.photoUrl.toString()
        var userName = currentUser?.displayName

        if (userName == null || userName == "null" || userName == "") userName = "SafeBuddy"

        _uiState.update { it.copy(photoUrl = photoUrl, userName = userName) }
    }


}
package com.aldeadavila.suggestionbox.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.model.Resource
import com.aldeadavila.suggestionbox.domain.repository.FirebaseRepository
import com.aldeadavila.suggestionbox.domain.repository.ReloadUserResponse
import com.aldeadavila.suggestionbox.domain.repository.RevokeAccessResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: FirebaseRepository
): ViewModel() {
    var revokeAccessResource by mutableStateOf<RevokeAccessResponse>(Resource.Success(false))
        private set
    var reloadUserResource by mutableStateOf<ReloadUserResponse>(Resource.Success(false))
        private set

    fun reloadUser() = viewModelScope.launch {
        reloadUserResource = Resource.Loading()
        reloadUserResource = repo.reloadFirebaseUser()
    }

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    fun signOut() = repo.signOut()

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResource = Resource.Loading()
        revokeAccessResource = repo.revokeAccess()
    }
}
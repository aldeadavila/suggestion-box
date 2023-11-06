package com.aldeadavila.suggestionbox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.suggestionbox.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: FirebaseRepository
): ViewModel() {

    init {
        getAuthState()
    }

    fun getAuthState() = repo.getAuthState(viewModelScope)
    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false
}
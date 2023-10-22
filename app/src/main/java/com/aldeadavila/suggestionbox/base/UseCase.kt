package com.aldeadavila.suggestionbox.base

import android.util.Log
import com.aldeadavila.suggestionbox.util.State

abstract class UseCase<Input : Any?, Output : Any> {

    protected abstract suspend fun invoke(input: Input?): State<Output>

    suspend fun execute(input: Input?): State<Output> {
        Log.d("UseCase: ","$this $input")
        return invoke(input)
    }
}

interface Inputs
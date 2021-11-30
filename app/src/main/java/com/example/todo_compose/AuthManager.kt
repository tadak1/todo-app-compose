package com.example.todo_compose

import com.example.todo_compose.model.State
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
class AuthManager(
    private val auth: FirebaseAuth = Firebase.auth
) {
    suspend fun signIn(email: String, password: String): Flow<State<AuthResult>> {
        return flow<State<AuthResult>> {
            emit(State.success(auth.signInWithEmailAndPassword(email, password).await()))
        }.catch { exception ->
            emit(State.failure(exception))
        }.onStart {
            State.loading<AuthResult>()
        }
    }

    suspend fun signUp(email: String, password: String): Result<AuthResult> {
        return runCatching {
            auth.createUserWithEmailAndPassword(email, password).await()
        }.onSuccess {
            Result.success(it)
        }.onFailure {
            Result.failure<AuthResult>(it)
        }
    }
}


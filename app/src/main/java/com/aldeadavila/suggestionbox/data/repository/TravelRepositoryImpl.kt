package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.domain.model.Travel
import com.aldeadavila.suggestionbox.domain.repository.TravelRepository
import com.aldeadavila.suggestionbox.domain.util.Resource
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject
import javax.inject.Named

class TravelRepositoryImpl @Inject constructor(
    @Named("Travels") private val travelsRef: CollectionReference
): TravelRepository {

    override fun getTravels(): Flow<Resource<List<Travel>>> = callbackFlow {
        trySend(Resource.Loading())

        val snapshotListener = travelsRef
            .orderBy("date")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    trySend(Resource.Error(e.message ?: "Error desconocido"))
                    return@addSnapshotListener
                }

                val travels = snapshot?.documents?.mapNotNull { document ->
                    document.toObject(Travel::class.java)?.apply {
                        id = document.id
                    }
                } ?: emptyList()

                trySend(Resource.Success(travels))
            }

        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getTravelsByUser(userId: String): Flow<Resource<List<Travel>>> = callbackFlow {
        trySend(Resource.Loading())

        val snapshotListener = travelsRef
            .whereEqualTo("userId", userId)
            .orderBy("date")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    trySend(Resource.Error(e.message ?: "Error desconocido"))
                    return@addSnapshotListener
                }

                val travels = snapshot?.documents?.mapNotNull { document ->
                    document.toObject(Travel::class.java)?.apply {
                        id = document.id
                    }
                } ?: emptyList()

                trySend(Resource.Success(travels))
            }

        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun createTravel(travel: Travel): Flow<Resource<Travel>> = callbackFlow {
        trySend(Resource.Loading())
        
        try {
            travel.createdAt = Date()
            travel.updatedAt = Date()
            
            val documentReference = travelsRef.add(travel).await()
            travel.id = documentReference.id
            
            trySend(Resource.Success(travel))
        } catch (e: Exception) {
            trySend(Resource.Error(e.message ?: "Error desconocido"))
        }
        
        awaitClose()
    }

    override fun updateTravel(travel: Travel): Flow<Resource<Travel>> = callbackFlow {
        trySend(Resource.Loading())
        
        try {
            travel.updatedAt = Date()
            travelsRef.document(travel.id).set(travel).await()
            trySend(Resource.Success(travel))
        } catch (e: Exception) {
            trySend(Resource.Error(e.message ?: "Error desconocido"))
        }
        
        awaitClose()
    }

    override fun deleteTravel(id: String): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading())
        
        try {
            travelsRef.document(id).delete().await()
            trySend(Resource.Success(id))
        } catch (e: Exception) {
            trySend(Resource.Error(e.message ?: "Error desconocido"))
        }
        
        awaitClose()
    }
} 
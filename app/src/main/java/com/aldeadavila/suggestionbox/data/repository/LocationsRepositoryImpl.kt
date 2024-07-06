package com.aldeadavila.suggestionbox.data.repository

import com.aldeadavila.suggestionbox.core.Config.LOCATIONS
import com.aldeadavila.suggestionbox.domain.model.Location
import com.aldeadavila.suggestionbox.domain.model.Response
import com.aldeadavila.suggestionbox.domain.model.User
import com.aldeadavila.suggestionbox.domain.repository.LocationsRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class LocationsRepositoryImpl @Inject constructor(
    @Named(LOCATIONS) private val locationsRef: CollectionReference,
    @Named(LOCATIONS) private val storagelocationsRef: StorageReference,
): LocationsRepository {
    override fun getLocations(): Flow<Response<List<Location>>> = callbackFlow {

        val snapshopListener = locationsRef.addSnapshotListener { snapshot, e ->

            GlobalScope.launch(Dispatchers.IO) {
                val locationResponse = if (snapshot != null) {
                    val locations = snapshot.toObjects(Location::class.java)
                    val idUserArray = ArrayList<String>()
                    locations.forEach { location ->
                        idUserArray.add(location.location_id)
                    }

                    Response.Success(locations)
                } else {
                    Response.Failure(Exception(e?.message ?: "Error desconocido"))
                }
                trySend(locationResponse)
            }
        }
        awaitClose {
            snapshopListener.remove()
        }

    }
}
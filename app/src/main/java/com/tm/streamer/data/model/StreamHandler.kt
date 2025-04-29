package com.tm.streamer.data.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StreamHandler {
    private val _streamDataFlow = MutableStateFlow<List<StreamData>>(emptyList())
    val streamDataFlow = _streamDataFlow.asStateFlow()

    init {
        fetchStream()
    }

    fun fetchStream() {
        val firestore = Firebase.firestore
        firestore.collection("streams").addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            val streamList = mutableListOf<StreamData>()
            value?.forEach {
                val stream = it.toObject(StreamData::class.java)
                streamList.add(stream)
            }
            _streamDataFlow.value = streamList
        }
    }

    fun deleteStream(streamData: String) {
        val firestore = Firebase.firestore
        firestore.collection("streams").document(streamData).delete()
    }

    fun startStream(streamData: StreamData, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val firestore = Firebase.firestore
        firestore.collection("streams").document(streamData.liveID).set(streamData)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onError.invoke(it.exception?.message ?: "An error occurred")
                }
            }
    }
}
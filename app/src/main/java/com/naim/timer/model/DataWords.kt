package com.naim.timer.model

import com.google.firebase.firestore.DocumentSnapshot

data class
DataWords(val words: List<String> = emptyList(), val urlImg : String, val price : Long) {
    companion object {
        fun fromSnapshot(snapshot: DocumentSnapshot): DataWords {
            val words = snapshot["words"] as? List<String> ?: emptyList()
            val urlImg = snapshot["urlImg"] as? String ?: ""
            val price = snapshot["price"] as? Long ?: 0
            return DataWords(words, urlImg, price)
        }
    }
}
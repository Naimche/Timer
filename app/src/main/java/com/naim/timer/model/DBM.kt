package com.naim.timer.model

import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.Flow

class DBM {

    companion object {
        fun onLogin(email: String, password: String, callback: (Int) -> Unit) {
            try {
                if (email.isNotBlank() && password.isNotBlank()) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                callback(0)
                            } else {
                                callback(1)
                            }
                        }
                } else {
                    callback(2)
                }
            } catch (e: Exception) {
                callback(9)
            }
        }

        fun onRegister(email: String, password: String, callback: (Int) -> Unit) {
            try {
                if (email.isNotBlank() && password.isNotBlank()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Log.i("DBM", "onRegister: True ")
                                callback(0)
                            } else {
                                Log.i("DBM", "onRegister: False ")

                                callback(1)
                            }
                        }
                } else {
                    callback(1)
                }
            } catch (e: Exception) {
                callback(1)
            }
        }


        fun uploadWordsScript(name:String, words:List<String>, callback: (Int) -> Unit){
            try {
                val db = FirebaseFirestore.getInstance()
                val data = DataWords(words)
                db.collection("DataWords").document(name).set(data)
                    .addOnSuccessListener {
                        Log.d("DBM", "DocumentSnapshot added")
                        callback(0)
                    }
                    .addOnFailureListener { e ->
                        Log.w("DBM", "Error adding document", e)
                        callback(1)
                    }
            }catch (e:Exception){
                callback(1)
            }
        }

    }
}

package com.naim.timer.model

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DBM {

    companion object {
        fun onLogin(email: String, password: String, callback: (Int) -> Unit) {
            try {
                if (email.isNotBlank() && password.isNotBlank()) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                callback(0)

                            }
                        }.addOnFailureListener { e ->
                            Log.i("LOGIN", "onLogin: ${e}")
                            when (e) {
                                is FirebaseAuthInvalidCredentialsException -> {
                                    callback(1)
                                }
                                is FirebaseAuthInvalidUserException -> {
                                    callback(1)
                                }
                                is FirebaseTooManyRequestsException -> {
                                    callback(4)
                                }
                                is FirebaseNetworkException -> {
                                    callback(3)
                                }
                                is FirebaseAuthException -> {
                                    callback(9)
                                }
                                else -> {
                                    callback(9)
                                }
                            }
                        }
                } else {
                    callback(2)
                }
            } catch (e: Exception) {
                callback(9)
            }
        }

        fun onRegister(email: String, password: String, nick: String, callback: (Int) -> Unit) {
            try {
                if (email.isNotBlank() && password.isNotBlank()) {
                    val mAuth = FirebaseAuth.getInstance()
                    mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            val fireStore = FirebaseFirestore.getInstance()
                            if (it.isSuccessful) {
                                Log.i("DBM", "onRegister: True ")
                                val userAuth = mAuth.currentUser
                                val user = User(nick, listOf("Personas", "Deportes","Peliculas", "Situaciones"))
                                fireStore.collection("User").document(userAuth!!.uid).set(user)
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




        fun uploadWordsScript(name: String, words: List<String>, callback: (Int) -> Unit) {
            try {
                val db = FirebaseFirestore.getInstance()
                val data = DataWords(words, "")
                db.collection("DataWords").document(name).set(data)
                    .addOnSuccessListener {
                        Log.d("DBM", "DocumentSnapshot added")
                        callback(0)
                    }
                    .addOnFailureListener { e ->
                        Log.w("DBM", "Error adding document", e)
                        callback(1)
                    }
            } catch (e: Exception) {
                callback(1)
            }
        }

        suspend fun getImagen(idDlc: String) = suspendCoroutine { c ->
            val img = Firebase.storage.reference.child("Dlcs/$idDlc.png")
            if (Firebase.auth.uid != null) {
                img.downloadUrl.addOnSuccessListener {
                    Log.i("DBM", "getImagen: $it")
                    c.resume(it.toString())
                }.addOnFailureListener {
                    Log.i("DBM", "getImagen: $it")
                    c.resume("")
                }
            }
        }

        fun getAllDlcs(): Flow<List<DataWords>> = flow {
            val list = mutableListOf<DataWords>()
            val db = FirebaseFirestore.getInstance()
            db.collection("DataWords").get().await().forEach {
                if (it.id.contains("Dlc")) {
                    Log.i("DLC", "getAllDlcs: ${it.id}")
                    Log.i("DLC", "getAllDlcs: $it")
                    //Log.i("DLC", "getAllDlcs: ${it.toObject(DataWords::class.java)}")
                    //list.add(it.toObject(DataWords::class.java))
                    val dataWords = DataWords.fromSnapshot(it)
                    list.add(dataWords)
                    Log.i("DLC", "getAllDlcs: $it ${list})}")
                }

            }
            emit(list)
        }

        fun getAllCategoriesWthAccess():Flow<Map<String,DataWords>> = flow{
            Log.i("CATEGORIES", "getAllCategoriesWthAccess Start")
            val map = mutableMapOf<String,DataWords>()
            val db = FirebaseFirestore.getInstance()
            val user = FirebaseAuth.getInstance().currentUser?.let { getUserData(it.uid).first() }
            Log.i("CATEGORIES", user.toString())
            if (user != null) {
                user.dataWordsKey?.forEach { key ->
                    db.collection("DataWords").document(key).get().await().let {
                        val dataWords = DataWords.fromSnapshot(it)
                        Log.i("DBM", it.id)
                        map[it.id] = dataWords
                    }
                }
            }
            emit(map)

        }

        fun getUserData(userUid: String): Flow<User> = callbackFlow {
            val db = FirebaseFirestore.getInstance()
            var user = User()
            Log.i("GetUser", "User Uid: $userUid")
            db.collection("User").document(userUid).get().addOnSuccessListener {
                Log.i("User", "Obtenido información")
                val data = it.toObject(User::class.java)
                if (data != null) user = data
                trySend(user)
            }
            awaitClose { channel.close() }
        }


    }
}

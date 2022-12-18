package com.zahideaksak.smartagenda.model

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ToDoDao {

    private val auth = Firebase.auth
    private val db = FirebaseFirestore.getInstance()
    val taskCollection = db.collection("Tasks")

    fun addNote(due: String, task: String) {
        val currentUserId = auth.currentUser!!.uid
        val note = ToDoModel(currentUserId, task, due)
        taskCollection.document().set(note)
    }
}
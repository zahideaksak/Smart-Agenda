package com.zahideaksak.smartagenda

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.zahideaksak.smartagenda.adapter.ToDoAdapter
import com.zahideaksak.smartagenda.databinding.ActivityMainBinding
import com.zahideaksak.smartagenda.model.ToDoDao
import com.zahideaksak.smartagenda.model.ToDoModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rcView: RecyclerView
    private lateinit var toDoDao: ToDoDao
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rcView = binding.rcView
        toDoDao = ToDoDao()
        auth = Firebase.auth

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddNewTask::class.java)
            startActivity(intent)
        }

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {

        rcView.layoutManager = LinearLayoutManager(this)

        val todoCollection = toDoDao.taskCollection
        val currentUserId = auth.currentUser?.uid

        val query = todoCollection.whereEqualTo("uid", currentUserId)
            .orderBy("text", Query.Direction.ASCENDING)

        val recyclerViewOption =
            FirestoreRecyclerOptions.Builder<ToDoModel>().setQuery(query, ToDoModel::class.java)
                .build()

        adapter = ToDoAdapter(recyclerViewOption)
        rcView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}
package com.example.e_library.data.repoImpl

import com.example.e_library.common.Book
import com.example.e_library.common.BookCategory
import com.example.e_library.common.ResultState
import com.example.e_library.domain.repo.AllBookRepo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AllBookRepoImpl @Inject constructor(val firebaseDatabase : FirebaseDatabase) : AllBookRepo {
    override fun getAllBooks(): Flow<ResultState<List<Book>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var items:List<Book> = emptyList()
                items = snapshot.children.map { value ->
                    value.getValue(Book::class.java)!!
                }

                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }

        }

        firebaseDatabase.reference.child("Book").addValueEventListener(valueEvent)

        awaitClose{
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }

    }



    override fun getAllCategory(): Flow<ResultState<List<BookCategory>>> = callbackFlow() {
        trySend(ResultState.Loading)

        val valueEvent = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var items :List<BookCategory> = emptyList()
                items = snapshot.children.map{ value ->
                    value.getValue(BookCategory::class.java)!!

                }

                trySend(ResultState.Success(items))

            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }

        }

        firebaseDatabase.reference.child("BookCategory").addValueEventListener(valueEvent)


        awaitClose{
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }

    override fun getAllBooksByCategory(category: String): Flow<ResultState<List<Book>>> = callbackFlow{

        trySend(ResultState.Loading)

        val valueEvent = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var items: List<Book> = emptyList()
                items = snapshot.children.filter{value ->
                    value.getValue(Book::class.java)!!.category == category
                }.map{value->
                    value.getValue(Book::class.java)!!
                }


                trySend(ResultState.Success(items))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.toException()))
            }

        }

        firebaseDatabase.reference.child("Book").addValueEventListener(valueEvent)
        awaitClose{
            firebaseDatabase.reference.removeEventListener(valueEvent)
            close()
        }
    }
}
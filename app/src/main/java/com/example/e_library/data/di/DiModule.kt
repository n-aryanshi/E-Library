package com.example.e_library.data.di

import com.example.e_library.data.repoImpl.AllBookRepoImpl
import com.example.e_library.domain.repo.AllBookRepo
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    fun provideFirebaseDatabase() : FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun provideFirebaseStorage() : FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

    @Provides
    fun provideAllBookRepo(firebaseDatabase: FirebaseDatabase) : AllBookRepo{
        return AllBookRepoImpl(firebaseDatabase)
    }
}
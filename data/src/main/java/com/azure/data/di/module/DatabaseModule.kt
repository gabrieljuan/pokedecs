package com.azure.data.di.module

import android.content.Context
import com.azure.data.di.qualifier.PokeCollection
import com.azure.data.di.qualifier.UserCollection
import com.couchbase.lite.Collection
import com.couchbase.lite.CouchbaseLite
import com.couchbase.lite.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        CouchbaseLite.init(context)
        return Database("pokedecs")
    }

    @Provides
    @Singleton
    @PokeCollection
    fun providePokeCollection(
        database: Database
    ): Collection {
        return database.getCollection("poke_collection")
            ?: database.createCollection("poke_collection")
    }

    @Provides
    @Singleton
    @UserCollection
    fun provideUserCollection(
        database: Database
    ): Collection {
        return database.getCollection("user_collection")
            ?: database.createCollection("user_collection")
    }
}
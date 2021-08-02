package com.mihaelanegoita.recash.core;

import android.content.Context;

import androidx.room.Room;

import com.mihaelanegoita.recash.data.TransactionRepository;
import com.mihaelanegoita.recash.data.dao.TransactionDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public class DatabaseModule {
    @Provides
    @Singleton
    ApplicationDatabase provideApplicationDatabase(@ApplicationContext Context applicationContext) {
        return Room.databaseBuilder(applicationContext, ApplicationDatabase.class, "Recash").fallbackToDestructiveMigration().build();
    }

    @Provides
    TransactionDao provideTransactionDao(ApplicationDatabase applicationDatabase) {
        return applicationDatabase.transactionDao();
    }
}

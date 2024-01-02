package com.example.theboythemolethefoxandthehorse.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.theboythemolethefoxandthehorse.R
import com.example.theboythemolethefoxandthehorse.domain.model.Quote
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Database(
    entities = [Quote::class],
    version = 1
)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {

        // Singleton
        @Volatile
        private var Instance: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    QuoteDatabase::class.java,
                    "quotes.db"
                )
                    /**
                 * Setting this option in your app's database builder means that Room
                 * permanently deletes all data from the tables in your database when it
                 * attempts to perform a migration with no defined migration path.
                 */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }

        private fun populateDatabase(quoteDao: QuoteDao?) {
            runBlocking {
                launch {

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote1,
                            imageResourceId = R.drawable.image1,
                            dayOfQuote = 1,
                        ),
                    )

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote2,
                            imageResourceId = R.drawable.image2,
                            dayOfQuote = 2
                        ),
                    )

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote3,
                            imageResourceId = R.drawable.image3,
                            dayOfQuote = 3
                        ),
                    )

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote4,
                            imageResourceId = R.drawable.image4,
                            dayOfQuote = 4
                        ),
                    )

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote5,
                            imageResourceId = R.drawable.image5,
                            dayOfQuote = 5
                        ),
                    )

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote6,
                            imageResourceId = R.drawable.image6,
                            dayOfQuote = 6
                        ),
                    )

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote7,
                            imageResourceId = R.drawable.image7,
                            dayOfQuote = 7
                        ),
                    )

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote8,
                            imageResourceId = R.drawable.image8,
                            dayOfQuote = 8
                        ),
                    )

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote9,
                            imageResourceId = R.drawable.image9,
                            dayOfQuote = 9
                        ),
                    )

                    quoteDao?.upsertQuote(
                        Quote(
                            stringResourceId = R.string.quote10,
                            imageResourceId = R.drawable.image10,
                            dayOfQuote = 10
                        ),
                    )

                }
            }
        }

    }
}
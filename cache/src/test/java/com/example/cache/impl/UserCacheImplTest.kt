package com.example.cache.impl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cache.dataFactory.CacheDataFactory.makeUserCacheEntityList
import com.example.cache.mappers.UserCacheModelMapper
import com.example.cache.room.UserDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("PublicApiImplicitType")
@RunWith(AndroidJUnit4::class)
class UserCacheImplTest {

    private lateinit var sut: UserCacheImpl


    private lateinit var database: UserDatabase

    @Before
    fun setup() = runBlocking {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()

        sut = UserCacheImpl(
            database.userDao(),
            UserCacheModelMapper()
        )
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `saveCacheList adds a list of users to the database`() = runBlocking {
        val expected = makeUserCacheEntityList(2)

        sut.saveCacheList(expected)

        val actual = sut.getUserList().first()

        assertThat(expected.size).isEqualTo(actual.size)
    }
}
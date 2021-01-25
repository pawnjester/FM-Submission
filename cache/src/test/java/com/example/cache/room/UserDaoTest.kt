package com.example.cache.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cache.dataFactory.CacheDataFactory.makeUserCacheModelList
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("PublicApiImplicitType")
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var sut: UserDao
    private lateinit var database: UserDatabase

    @Before
    fun setup() = runBlocking {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()

        sut = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `save a user`() = runBlocking {
        sut.saveUser(makeUserCacheModelList(2))

        val actual = sut.getUsers()
        assertThat(actual.size).isEqualTo(2)
    }
}
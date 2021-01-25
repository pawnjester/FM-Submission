package com.example.cache.mappers

import com.example.cache.dataFactory.CacheDataFactory.makeUserCacheEntity
import com.example.cache.dataFactory.CacheDataFactory.makeUserCacheModel
import com.example.cache.models.UserCacheModel
import com.example.data.model.UserEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UserCacheModelMapperTest {

    private val sut = UserCacheModelMapper()

    @Test
    fun `mapToModel maps entity to model`() {
        val expected = makeUserCacheEntity()

        val actual: UserCacheModel = sut.mapToModel(expected)
        assertThat(actual.email).isEqualTo(expected.email)
    }

    @Test
    fun `mapToEntity maps model to entity`() {
        val expected = makeUserCacheModel()

        val actual: UserEntity = sut.mapToEntity(expected)

        assertThat(actual.email).isEqualTo(expected.email)
    }
}
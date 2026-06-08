package com.azure.feature

import com.azure.domain.model.PokeDetail
import com.azure.domain.usecase.GetPokeDetailUseCase
import com.azure.domain.util.DataResult
import com.azure.feature.detail.PokeDetailViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

class PokeDetailViewModelTest : BaseViewModelTest<PokeDetailViewModel>() {

    private val getPokeDetailUseCase = mockk<GetPokeDetailUseCase>()
    override val viewModel: PokeDetailViewModel = PokeDetailViewModel(getPokeDetailUseCase)

    @Test
    fun `when getPokeDetail success returns details of poke`() = runTest {
        // GIVEN
        val pokeName = "pikachu"
        val pokeDetail = mockk<PokeDetail>()
        coEvery { getPokeDetailUseCase(any()) } returns DataResult.Success(pokeDetail)

        // WHEN
        viewModel.getPokeDetail(pokeName)

        // THEN
        viewModel.uiState.value.apply {
            assertEquals(pokeDetail, this.pokeDetail)
            assertFalse(isLoading)
            assertNull(errorMessage)
        }
        coVerify { getPokeDetailUseCase("pikachu") }
    }

    @Test
    fun `when getPokeDetail failed returns exception`() = runTest {
        // GIVEN
        val pokeName = "pikachu"
        val errorMessage = "error"
        coEvery { getPokeDetailUseCase(any()) } returns DataResult.Exception(Throwable(errorMessage))

        // WHEN
        viewModel.getPokeDetail(pokeName)

        // THEN
        viewModel.uiState.value.apply {
            assertEquals(PokeDetail(), this.pokeDetail)
            assertFalse(isLoading)
            assertEquals(errorMessage, this.errorMessage)
        }
        coVerify { getPokeDetailUseCase("pikachu") }
    }
}
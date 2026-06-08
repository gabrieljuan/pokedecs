package com.azure.feature

import androidx.lifecycle.ViewModel
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

abstract class BaseViewModelTest<VM: ViewModel> {

    protected val testScope = TestScope()

    val testDispatcher = UnconfinedTestDispatcher(testScope.testScheduler)

    protected abstract val viewModel: VM

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        clearAllMocks()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
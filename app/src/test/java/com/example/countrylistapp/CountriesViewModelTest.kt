package com.example.countrylistapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.countrylistapp.data.CountriesRepository
import com.example.countrylistapp.model.Country
import com.example.countrylistapp.model.CountryItem
import com.example.countrylistapp.model.Currency
import com.example.countrylistapp.model.Language
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class CountriesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var mockRepository: CountriesRepository

    private lateinit var viewModel: CountriesViewModel

    @Before
    fun setUp() {
        mockRepository = mock()

        Dispatchers.setMain(testDispatcher)

        viewModel = CountriesViewModel(mockRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchCountries should call repository's fetchCountries`() = runTest {
        viewModel.fetchCountries()

        testDispatcher.scheduler.advanceUntilIdle()

        verify(mockRepository).fetchCountries()
    }

    @Test
    fun `countries LiveData emits data from repository`() = runTest {
        val mockCountries = listOf(
            Country(
                capital = "Kabul",
                code = "AF",
                currency = Currency("AFN", "Afghan afghani", "؋"),
                flag = "https://restcountries.eu/data/afg.svg",
                language = Language("ps", "Pashto"),
                name = "Afghanistan",
                region = "AS"
            )
        )

        val liveData = MutableLiveData<List<Country>>()
        liveData.value = mockCountries

        whenever(mockRepository.getCountries()).thenReturn(liveData)

        val viewModel = CountriesViewModel(mockRepository)

        val observedCountryItems = mutableListOf<List<CountryItem>>()
        viewModel.countries.observeForever { countryItems ->
            observedCountryItems.add(countryItems)
        }

        assertEquals(1, observedCountryItems.size)
        assertEquals(mockCountries, observedCountryItems[0])
    }
}
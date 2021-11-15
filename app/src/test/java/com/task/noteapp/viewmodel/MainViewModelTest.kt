package com.task.noteapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.noteapp.db.NotesDao
import com.task.noteapp.db.NotesDatabase
import com.task.noteapp.db.entities.NoteEntity
import com.task.noteapp.di.getTestComponent
import com.task.noteapp.repository.NotesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest : KoinTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    lateinit var mNotesRepository: NotesRepository

    lateinit var mMainViewModel: MainViewModel

    val noteDatabase by inject<NotesDatabase>()
    val noteDao by inject<NotesDao>()

    @Before
    fun setUp() {
        startKoin {
            modules(getTestComponent())
        }

        mNotesRepository = get()
        mMainViewModel = MainViewModel(mNotesRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllNotes() = runBlockingTest {
        coEvery { mNotesRepository.getAllNotes() } returns listOf(
            NoteEntity(
                Date().time,
                "Test Title",
                "Test Desc",
                null,
                false
            )
        )
        var result = mNotesRepository.getAllNotes()
        mMainViewModel.uiState.observeForever { }
        mMainViewModel.notes.observeForever { }

        assert(mMainViewModel.notes.value != null)
        assert(mMainViewModel.uiState.value == MainUIState.NoteList())
        assertEquals(mMainViewModel.notes.value!![0].title, "Test Title")
    }

    @Test
    fun addNote() {
    }

    @Test
    fun deleteNote() {
    }

    @Test
    fun addNewNote() {
    }
}
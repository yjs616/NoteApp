package com.example.noteapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.Models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)   //충돌이 발생할 경우 덮어쓰기
    suspend fun insert(note: Note)


    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notes_table order by id ASC")      //id 오름차순 정렬
    fun getAllNotes() : LiveData<List<Note>>

    @Query("UPDATE notes_table Set title= :title, note= :note WHERE id= :id") // :뒤에 있는 부분은 변수이다
    suspend fun update(id: Int?, title : String?, note: String?)
}
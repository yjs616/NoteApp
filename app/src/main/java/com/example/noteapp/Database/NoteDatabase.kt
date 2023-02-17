package com.example.noteapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.Models.Note
import com.example.noteapp.utilities.DATABASE_NAME

//Database 연결하는 부분
//스키마를 확인할 필요가 없으며 경고를 나타나지 않게 하기 위해서 exportSchema= false를 추가해준다
@Database(entities = arrayOf(Note::class), version =1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDao  //NoteDao 불러오기

    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase{
            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }

}
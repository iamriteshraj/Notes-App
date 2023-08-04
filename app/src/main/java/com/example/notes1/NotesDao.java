package com.example.notes1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("select * from note")
    List<Note> getNotes();

    @Insert
    void addNotes(Note note);

    @Delete
    void deleteNotes(Note note);


}

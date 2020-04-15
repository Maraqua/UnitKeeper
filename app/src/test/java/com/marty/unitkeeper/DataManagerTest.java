package com.marty.unitkeeper;

import org.junit.Test;

import static org.junit.Assert.*;

public
class DataManagerTest {

    @Test
    public
    void createNewNote () {
        DataManager dm = DataManager.getInstance ();
        final CourseInfo course = dm.getCourse ( "android_async" );
        final String noteTitle = "Test Note Title";
        final String noteText = "This is Note Text for test";

        int noteIndex = dm.createNewNote ();
        NoteInfo newNote = dm.getNotes ().get(noteIndex);
        newNote.setCourse ( course );
        newNote.setTitle ( noteTitle );
        newNote.setText ( noteText );

        NoteInfo compareNote = dm.getNotes ().get ( noteIndex );
        assertEquals ( compareNote.getCourse (),course );
        assertEquals ( compareNote.getTitle (),noteTitle );
        assertEquals ( compareNote.getText (),noteText );
    }
}
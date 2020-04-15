package com.marty.unitkeeper;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

public
class NoteActivityViewModel extends ViewModel {
    public static final String ORIGINAL_NOTE_COURSE_ID ="com.marty.unitkeeper.ORIGINAL_NOTE_COURSE_ID";
    public static final String ORIGINAL_NOTE_TITLE ="com.marty.unitkeeper.ORIGINAL_NOTE_TITLE";
    public static final String ORIGINAL_NOTE_TEXT ="com.marty.unitkeeper.ORIGINAL_NOTE_TEXT";
    public String mOriginalNoteCourseId;
    public String mOriginalNoteTitle;
    public  String mOriginalNoteText;
    public boolean mIsNewlyCreated = true;


    public
    void saveState ( Bundle outState ) {
        //all contants we want to write to bundle are string so use putString
        outState.putString (ORIGINAL_NOTE_COURSE_ID ,mOriginalNoteCourseId );
        outState.putString (ORIGINAL_NOTE_TITLE, mOriginalNoteTitle);
        outState.putString ( ORIGINAL_NOTE_TEXT,mOriginalNoteText );

    }
    public void restoreState(Bundle inState){

        mOriginalNoteCourseId  = inState.getString (ORIGINAL_NOTE_COURSE_ID );
        mOriginalNoteTitle = inState.getString ( ORIGINAL_NOTE_TITLE );
        mOriginalNoteText = inState.getString ( ORIGINAL_NOTE_TEXT );
    }
}

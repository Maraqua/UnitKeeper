package com.marty.unitkeeper;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.Espresso.pressBack;
import static org.hamcrest.Matcher.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;


@RunWith ( AndroidJUnit4.class )
public
class NoteCreationTest {
    static DataManager sDataManager ;
    @BeforeClass
    public  static void classSetUp(){
        sDataManager = DataManager.getInstance ();
    }
@Rule
public
        //launch the activity
    ActivityTestRule<NoteListActivity> mNoteListActivityActivityTestRule =
        new ActivityTestRule<> ( NoteListActivity.class );
@Test
    public  void createNewNote(){
    final CourseInfo course = sDataManager.getCourse ( "java_lang" );
    final String noteTitle = "Test Note Title";
    final String noteText = "This is the body of our note";

//    //get view
//    ViewInteraction fabNewNote = onView ( withId ( R.id.fab ) );
//    //perform action
//    fabNewNote.perform ( click () );
//perform all in one line
    onView ( withId ( R.id.fab ) ).perform ( click () );
    //now we are on NoteActivity
    //click on spinner
    onView ( withId ( R.id.spinner_courses ) ).perform ( click () );
    //make selection
    onData ( allOf(instanceOf(CourseInfo.class), equalTo ( course )) ).perform ( click () );
    onView ( withId ( R.id.spinner_courses ) ).check ( matches ( withSpinnerText ( containsString ( course.getTitle () ) ) ) );
    onView ( withId ( R.id.text_note_title ) ).perform ( typeText ( noteTitle ) ).check ( matches ( withText ( containsString ( noteTitle ) ) ) );
    onView ( withId ( R.id.text_note_text ) ).perform ( typeText ( noteText ) ,
                                                        //close soft keyboard
                                                        closeSoftKeyboard ());
    onView ( withId ( R.id.text_note_text ) ).check ( matches ( withText ( containsString ( noteText ) ) ) );

    pressBack ();
    int noteIndex = sDataManager.getNotes ().size () - 1;
    NoteInfo note = sDataManager.getNotes ().get ( noteIndex );
    assertEquals ( course,note.getCourse () );
    assertEquals ( noteTitle,note.getTitle () );
    assertEquals ( noteText,note.getText () );


}
}

package com.marty.unitkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class NoteActivity extends AppCompatActivity {
    public static final String NOTE_POSITON = "com.marty.unitkeeper.NOTE_POSITON";
    public static final int POSITION_NOT_SET = -1;
    @BindView(R.id.text_note_title)
    EditText mTextNoteTitle;
    @BindView(R.id.text_note_text)
    EditText mTextNoteText;
    private NoteInfo mNote;
    private boolean mIsNewNote;
    private Spinner mSpinnerCourses;

    @Override
    protected
    void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_note );
        ButterKnife.bind ( this );
        Toolbar toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        mSpinnerCourses = findViewById ( R.id.spinner_courses );

        List<CourseInfo> courses = DataManager.getInstance ().getCourses ();
        ArrayAdapter<CourseInfo> adapterCourses =
                new ArrayAdapter<> ( this, android.R.layout.simple_spinner_item, courses );
        adapterCourses.setDropDownViewResource ( android.R.layout.simple_spinner_dropdown_item );
        mSpinnerCourses.setAdapter ( adapterCourses );
        readDisplayStateValues ();
if(!mIsNewNote){
    displayNote( mSpinnerCourses, mTextNoteTitle, mTextNoteText);

}
    }

    private
    void displayNote ( Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText ) {
        List<CourseInfo> courses = DataManager.getInstance ().getCourses ();
        int courseIndex = courses.indexOf ( mNote.getCourse () );
        spinnerCourses.setSelection ( courseIndex );
        textNoteTitle.setText ( mNote.getTitle () );
        textNoteText.setText ( mNote.getText () );
    }

    private
    void readDisplayStateValues () {
        Intent intent = getIntent ();
        int position = intent.getIntExtra ( NOTE_POSITON , POSITION_NOT_SET );
        mIsNewNote = position == POSITION_NOT_SET;
        if(!mIsNewNote){
            mNote = DataManager.getInstance ().getNotes ().get ( position );

        }
    }

    @Override
    public
    boolean onCreateOptionsMenu ( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate ( R.menu.menu_note, menu );
        return true;
    }

    @Override
    public
    boolean onOptionsItemSelected ( MenuItem item ) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send_mail) {
            sendEmail();
            return true;
        }

        return super.onOptionsItemSelected ( item );
    }

    private
    void sendEmail () {
    CourseInfo course = (CourseInfo) mSpinnerCourses.getSelectedItem ();
    String subject = mTextNoteTitle.getText ().toString ();
    String text = "Checkout the course \""+ course.getTitle ()+"\"\n "+mTextNoteText.getText ();
    Intent intent = new Intent ( Intent.ACTION_SEND );
    intent.setType ( "message/rfc2822 ");
    intent.putExtra ( Intent.EXTRA_SUBJECT,subject );
    intent.putExtra ( Intent.EXTRA_TEXT,text );
    startActivity (intent  );

    }
}

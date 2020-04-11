package com.marty.unitkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public
class NoteListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list_notes)
    ListView listNotes;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected
    void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_note_list );
        ButterKnife.bind ( this );
        Toolbar toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        FloatingActionButton fab = findViewById ( R.id.fab );
        fab.setOnClickListener ( new View.OnClickListener () {
            @Override
            public
            void onClick ( View view ) {
                Snackbar.make ( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction ( "Action", null ).show ();
            }
        } );
        initialiseDisplayContents();
    }

    private
    void initialiseDisplayContents () {
        ListView listnotes = findViewById ( R.id.list_notes );
        List<NoteInfo> notes = DataManager.getInstance ().getNotes ();
        ArrayAdapter<NoteInfo> adapterNotes = new ArrayAdapter<> ( this,android.R.layout.simple_list_item_1,notes);
        listNotes.setAdapter ( adapterNotes );
        listNotes.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public
            void onItemClick ( AdapterView<?> adapterView, View view, int position, long l ) {
                Intent intent = new Intent( NoteListActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        } );


    }

}

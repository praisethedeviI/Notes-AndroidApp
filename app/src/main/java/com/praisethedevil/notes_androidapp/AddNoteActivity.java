package com.praisethedevil.notes_androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    private EditText text;

    private SharedPreferencesHelper mSharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        text = findViewById(R.id.text);
        mSharedPreferencesHelper = new SharedPreferencesHelper(AddNoteActivity.this);
    }

    public void onAddClick(View view) {
        if (!text.getText().toString().isEmpty()) {
            addToArray();
            startActivity(new Intent(AddNoteActivity.this, NotesActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            this.finish();
        }
    }

    private void addToArray() {
        Note note = new Note(text.getText().toString());
        if (NotesActivity.leftHeight <= NotesActivity.rightHeight) {
            mSharedPreferencesHelper.AddNote(note, 0);
        } else {
            mSharedPreferencesHelper.AddNote(note, 1);
        }
    }
}
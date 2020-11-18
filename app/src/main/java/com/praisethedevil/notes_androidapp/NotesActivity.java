package com.praisethedevil.notes_androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    public static ArrayList<Note> leftList = new ArrayList<>();
    public static ArrayList<Note> rightList = new ArrayList<>();
    public static Integer leftHeight = 0;
    public static Integer rightHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        LinearLayout leftLayout = findViewById(R.id.leftNotesLayout);
        LinearLayout rightLayout = findViewById(R.id.rightNotesLayout);

        leftLayout.post(() -> leftHeight = leftLayout.getHeight());
        rightLayout.post(() -> rightHeight = rightLayout.getHeight());

        for (Note note : leftList) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.leftNotesLayout, NoteFragment.newInstance(note)).commit();
            }
        }
        for (Note note : rightList) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.rightNotesLayout, NoteFragment.newInstance(note)).commit();
            }
        }
        

    }

    public void onAddClick(View view) {
        startActivity(new Intent(NotesActivity.this, AddNoteActivity.class));
    }
}
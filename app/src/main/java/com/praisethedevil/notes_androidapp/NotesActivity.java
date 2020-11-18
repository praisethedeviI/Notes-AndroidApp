    package com.praisethedevil.notes_androidapp;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.LinearLayout;

    import java.util.ArrayList;

    public class NotesActivity extends AppCompatActivity {

        public static ArrayList<ArrayList<Note>> lists;

        public static Integer leftHeight = 0;
        public static Integer rightHeight = 0;

        public SharedPreferencesHelper mSharedPreferencesHelper;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_notes);

            mSharedPreferencesHelper = new SharedPreferencesHelper(NotesActivity.this);
            lists = mSharedPreferencesHelper.getNotes();
            
            LinearLayout leftLayout = findViewById(R.id.leftNotesLayout);
            LinearLayout rightLayout = findViewById(R.id.rightNotesLayout);

            leftLayout.post(() -> leftHeight = leftLayout.getHeight());
            rightLayout.post(() -> rightHeight = rightLayout.getHeight());

            for (Note note : lists.get(0)) {
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction().add(R.id.leftNotesLayout, NoteFragment.newInstance(note)).commit();
                }
            }
            for (Note note : lists.get(1)) {
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction().add(R.id.rightNotesLayout, NoteFragment.newInstance(note)).commit();
                }
            }
        }

        public void onAddClick(View view) {
            startActivity(new Intent(NotesActivity.this, AddNoteActivity.class));
        }
    }
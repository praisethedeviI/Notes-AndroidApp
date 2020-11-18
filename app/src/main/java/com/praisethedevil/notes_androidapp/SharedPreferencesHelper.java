package com.praisethedevil.notes_androidapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferencesHelper {
    public static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    public static final String NOTES_KEY = "NOTES_KEY";
    public static final Type NOTES_TYPE = new TypeToken<ArrayList<ArrayList<Note>>>() {
    }.getType();

    private final SharedPreferences mSharedPreferences;
    private final Gson mGson = new Gson();

    public SharedPreferencesHelper(Context ctx) {
        mSharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public ArrayList<ArrayList<Note>> getNotes() {
        ArrayList<ArrayList<Note>> notes = mGson.fromJson(mSharedPreferences.getString(NOTES_KEY, ""), NOTES_TYPE);
        return notes == null ? new ArrayList<ArrayList<Note>>() {
            {
                add(new ArrayList<>());
                add(new ArrayList<>());
            }
        } : notes;
    }

    public void AddNote(Note note, int pos) {
        ArrayList<ArrayList<Note>> notes = getNotes();
        notes.get(pos).add(note);
        mSharedPreferences.edit().putString(NOTES_KEY, mGson.toJson(notes, NOTES_TYPE)).apply();
    }
}

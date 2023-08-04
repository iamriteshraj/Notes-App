package com.example.notes1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton FAB;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FAB = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        databaseHelper = DatabaseHelper.getInstance(this);
        showNotes();
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_note_layout);
                dialog.show();
                Toast.makeText(MainActivity.this, ".......", Toast.LENGTH_SHORT).show();

                EditText editTextTitle, editTextNotes;
                Button etbutton;
                editTextTitle = dialog.findViewById(R.id.ETtitle);
                editTextNotes = dialog.findViewById(R.id.ETnotes);
                etbutton = dialog.findViewById(R.id.ETbtn);


                etbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String title = editTextTitle.getText().toString();
                        String content = editTextNotes.getText().toString();

                        if (!title.equals("")) {
                            databaseHelper.notesDao().addNotes(new Note(content, title));
                            showNotes();
                            dialog.dismiss();
                        } else
                            Toast.makeText(MainActivity.this, "Title ", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });

    }

    public void showNotes() {
        ArrayList<Note> arrNotes = (ArrayList<Note>) databaseHelper.notesDao().getNotes();

        if (arrNotes.size() > 0) {

            recyclerView.setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(new RecyclerNotesAdapter(this, arrNotes,databaseHelper));
    }
}
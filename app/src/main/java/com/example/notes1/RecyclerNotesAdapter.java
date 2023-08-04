package com.example.notes1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerNotesAdapter extends RecyclerView.Adapter<RecyclerNotesAdapter.Viewholder> {
    Context context;
    ArrayList<Note> arrNotes = new ArrayList<>();
    DatabaseHelper databaseHelper;


    RecyclerNotesAdapter(Context context, ArrayList<Note> arrNotes, DatabaseHelper databaseHelper) {

        this.context = context;
        this.arrNotes = arrNotes;
        this.databaseHelper = databaseHelper;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.note_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {


        holder.txtTitle.setText(arrNotes.get(position).getTitle());
        holder.txtContent.setText(arrNotes.get(position).getContent());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                deleteitem(position);

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtContent;
        CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtContent = itemView.findViewById(R.id.txtcontent);
            txtTitle = itemView.findViewById(R.id.txttitle);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }


    public void deleteitem(int position) {


        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure want to delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        databaseHelper.notesDao().deleteNotes(new Note(arrNotes.get(position).getId(), arrNotes.get(position).getTitle(), arrNotes.get(position).getContent()));
                        ((MainActivity)context).showNotes();

                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
}

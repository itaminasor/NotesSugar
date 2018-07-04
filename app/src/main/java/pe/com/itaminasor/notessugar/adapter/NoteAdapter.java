package pe.com.itaminasor.notessugar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.util.List;

import pe.com.itaminasor.notessugar.R;
import pe.com.itaminasor.notessugar.model.Note;
import pe.com.itaminasor.notessugar.repository.NoteRepository;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {




    private List<Note> notes;

    public NoteAdapter(){
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleText;
        public TextView contentText;
        public RelativeTimeTextView dateText;
        public CheckBox favoriteStar;

        public ViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
            contentText = (TextView) itemView.findViewById(R.id.content_text);
            dateText = (RelativeTimeTextView) itemView.findViewById(R.id.date_text);
            favoriteStar = (CheckBox) itemView.findViewById(R.id.favorite_star);
        }
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder viewHolder, int position) {
        final Note note = this.notes.get(position);
        viewHolder.titleText.setText(note.getTitle());
        viewHolder.contentText.setText(note.getContent());
        viewHolder.favoriteStar.setChecked(note.getFavorite());
        viewHolder.dateText.setReferenceTime(note.getDate().getTime());

        viewHolder.favoriteStar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                NoteRepository.update(b,note.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

}

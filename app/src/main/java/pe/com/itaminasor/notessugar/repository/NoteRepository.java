package pe.com.itaminasor.notessugar.repository;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import pe.com.itaminasor.notessugar.model.Note;
import pe.com.itaminasor.notessugar.model.User;

public class NoteRepository {

    private static final String TAG = NoteRepository.class.getSimpleName();


    public static List<Note> list(Long user) {

        List<Note> notes = SugarRecord.find(Note.class,"user = ? ",user.toString());

        return notes;
    }

    public static Note read(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        return note;
    }


    public static void create(Long user, String title, String content){
        Note note = new Note(null,title, content,
                new Date(), false,user, false);
        SugarRecord.save(note);
    }

    public static void update(Boolean favorite, Long id){
        Note note = SugarRecord.findById(Note.class, id);
        note.setFavorite(favorite);
        SugarRecord.save(note);
    }

    public static void delete(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        SugarRecord.delete(note);
    }

}

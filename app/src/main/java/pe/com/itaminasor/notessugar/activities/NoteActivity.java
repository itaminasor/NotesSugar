package pe.com.itaminasor.notessugar.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.com.itaminasor.notessugar.R;
import pe.com.itaminasor.notessugar.adapter.NoteAdapter;
import pe.com.itaminasor.notessugar.model.Note;
import pe.com.itaminasor.notessugar.model.User;
import pe.com.itaminasor.notessugar.repository.NoteRepository;
import pe.com.itaminasor.notessugar.repository.UserRepository;
import pe.com.itaminasor.notessugar.util.Constants;

public class NoteActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();



    private static final int REGISTER_FORM_REQUEST = 100;

    private RecyclerView notesRecyclerView;
    private SharedPreferences sharedPreferences;

    User user;
    private TextView titlenotesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.notesRecyclerView = (RecyclerView)findViewById(R.id.notes_list);
        this.notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.notesRecyclerView.setAdapter(new NoteAdapter());
        this.titlenotesText=(TextView)findViewById(R.id.titlenotes_text);


        Long iduser = sharedPreferences.getLong(Constants.IDUSER, 0);
        user = UserRepository.read(iduser);

        titlenotesText.setText("Bienvenido "+user.getFullname());

        refreshData();
    }

    public void refreshData(){
        NoteAdapter adapter = (NoteAdapter) notesRecyclerView.getAdapter();
        List<Note> notes = NoteRepository.list(user.getId());
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged(); // Refrezca los cambios en el RV
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_reload:
                refreshData();
                return true;
            case R.id.action_logout:
                //Toast.makeText(this, "Log out...", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                boolean success = editor.putBoolean(Constants.ISLOGGED, false).commit();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;

            case R.id.action_about:
                Toast.makeText(this, "Acerca de...", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showRegister(View view){
        //startActivity(new Intent(this, RegisterActivity.class));

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(new Intent(this, AddNoteActivity.class), REGISTER_FORM_REQUEST);
    }


    // return from RegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        refreshData();



    }



}

package com.example.shuvojoty.savewhatulike;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewNotes=(ListView)findViewById(R.id.main_listview_notes);
    }
    //create er kaj
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ///inflate menu to find menu from the resources
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main_new_note:
                //start NoteActivity in NewNote mode or create option

                //to bring the action of new activity
                //we should use Intent

                startActivity(new Intent(this,NoteActivity.class));

                break;
        }
        return true;
    }

    //override on resume
        @Override
        protected void onResume() {
            super.onResume();

            mListViewNotes.setAdapter(null);


            ArrayList<Note> notes=Utilities.getAllsavedNotes(this);

            //note ache kina dekhbo...thakle utilities k calll korbo




             if( notes.size()==0 || notes == null)
             {
                 Toast.makeText(this,"you have no notes",Toast.LENGTH_SHORT).show();
                 return;
             }
             else if(notes.size()>0){
                 NoteAdapter na= new NoteAdapter(this,R.layout.item_note,notes);
                 mListViewNotes.setAdapter(na);

                 mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                         String fileName=((Note)mListViewNotes.getItemAtPosition(i)).getmDateTime()
                                 +Utilities.FILE_EXTENSION;

                         Intent viewNoteIntent= new Intent(getApplicationContext(),NoteActivity.class);
                         viewNoteIntent.putExtra("NOTE_FILE",fileName);
                         startActivity(viewNoteIntent);
                     }
                 });
             }

        }


}

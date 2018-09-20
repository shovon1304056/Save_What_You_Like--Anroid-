package com.example.shuvojoty.savewhatulike;

import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    private EditText mEtTitle;
    private EditText mEtContent;

    private  String mNoteFileName;
    private Note mLoadednNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

       mEtTitle=(EditText) findViewById(R.id.note_et_title);
        mEtContent=(EditText) findViewById( R.id.note_et_content);

        mNoteFileName = getIntent().getStringExtra("NOTE_FILE");

        if(mNoteFileName != null && !mNoteFileName.isEmpty())
        {
            mLoadednNote=Utilities.getNoteByName(this,mNoteFileName);

            if(mLoadednNote !=null)
            {
                mEtTitle.setText(mLoadednNote.getmTitle());
                mEtContent.setText(mLoadednNote.getmContent());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_note_save:
                saveNote();

            case R.id.action_note_delete:
                deleteNote();


                break;
        }
        return true;
    }


    private  void saveNote(){

        Note note;

        if (mEtTitle.getText().toString().trim().isEmpty()
                || mEtContent.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this,"please enter a title and a content",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(mLoadednNote == null) {
             note = new Note(System.currentTimeMillis(), mEtTitle.getText().toString(),
                    mEtContent.getText().toString());
        }

        else{
             note = new Note(mLoadednNote
                    .getmDateTime(), mEtTitle.getText().toString(),
                    mEtContent.getText().toString());
        }
        if(Utilities.saveNote(this,note))
        {
            Toast.makeText(this,"note is saved",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"note cant be saved...check u have enough space in device",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    private void deleteNote() {

        if (mLoadednNote==null)
        {
            finish();
        }
        else
        {
            AlertDialog.Builder dialog=new AlertDialog.Builder(this)
                    .setTitle("delete")
                    .setMessage("DO YOU WANT TO DELETE???" + mEtTitle.getText().toString()
                    + ", are you sure??!!")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Utilities.deleteNOte(getApplicationContext(),mLoadednNote.getmDateTime()+
                                    Utilities.FILE_EXTENSION);

                            Toast.makeText(getApplicationContext(),
                                    mEtTitle.getText().toString()
                            + " is deleted " ,Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })

                    .setNegativeButton("no",null)
                    .setCancelable(false);
            dialog.show();
        }
    }
}

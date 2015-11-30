package com.example.greg.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Greg on 11/18/2015.
 */
public class GameInfoActivity extends Activity {
    private ArrayList<String> list;
    private static ArrayList<Character> characterList;
    private static String currentGameName;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_info_layout);

        list = new ArrayList<String>();
        characterList = new ArrayList<>();

        //Set the title of the activity to the name of the game that was selected
        currentGameName = GameArrayAdapter.currentGameName;
        TextView txtGameName = (TextView) (findViewById(R.id.txtGameName));
        txtGameName.setText(currentGameName);

        //set button onClick Listener
        setContentView(R.layout.game_info_layout);
        Button addCharacterBtn = (Button) findViewById(R.id.addCharacterBtn);
        addCharacterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                addCharacterClickHandler();
            }
        });
    }

    public void addCharacterClickHandler() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.character_creator, null);

        final EditText name = (EditText) textEntryView.findViewById(R.id.nameEditText);
        final EditText level = (EditText) textEntryView.findViewById(R.id.levelEditText);
        final EditText race = (EditText) textEntryView.findViewById(R.id.raceEditText);
        final RadioButton clYes = (RadioButton) textEntryView.findViewById(R.id.characterLevelYesRadioButton);
        final RadioButton clNo = (RadioButton) textEntryView.findViewById(R.id.characterLevelNoRadioButton);

        level.setFocusable(false);

         new AlertDialog.Builder(this)
                 .setTitle("Add Character")
                 .setMessage("Create Character Info")
                 .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         //get input values
                         String characterName = name.getText().toString();
                         int characterLevel = Integer.parseInt(level.getText().toString());
                         String characterRace = race.getText().toString();

                         //add character
                         addToCharacterList(characterName, characterRace, characterLevel, new String[]{"Elementalist"}, null);
                     }
                 })
                 .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         // do nothing
                     }
                 })
                 .setIcon(android.R.drawable.ic_dialog_alert)
                 .setView(textEntryView)
                 .show();
    }



    public static ArrayList<Character> getCharacterList() {
        return characterList;
    }

    public void addToCharacterList(String name, String race, int characterLevel, String[] job, int[] jobLevel){
        characterList.add(new Character(name, race, characterLevel, job, jobLevel));
        list.add(characterList.get(characterList.size() - 1).getName());
        displayList();
    }

    public void displayList(){
        //instantiate custom adapter
        GameInfoArrayAdapter adapter = new GameInfoArrayAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.gameInfoListView);
        lView.setAdapter(adapter);
    }
}
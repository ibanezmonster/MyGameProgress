package com.example.greg.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Greg on 11/18/2015.
 */
public class GameInfoActivity extends Activity {
    private ArrayList<String> list;
    private static ArrayList<Character> characterList;
    private static String currentGameName;

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
            public void onClick(View v) {
                addToCharacterList("Lyliava", "Human", 38, new String[]{"Elementalist"}, null); //for testing
            }
        });
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
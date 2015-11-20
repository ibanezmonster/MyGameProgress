package com.example.greg.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private static ArrayList<Game> gameListGames;
    private ArrayList<Button> gameListButtons;
    private Button btnGame;
    private Context context;
    private ArrayList<String> list = new ArrayList<String>();
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_list_layout);
        Log.d("Starting", "...");

        MyGameProgress app = (MyGameProgress) getApplication();
        gameListGames = app.getAllGames();
        Log.d("Game list", "Got game list");

        gameListGames = new ArrayList<>();
        gameListButtons = new ArrayList<>();

        //if no entries in game list, display a message stating that entries need to be added
//        if(gameListGames.isEmpty()){
//            ListView gamesListView = (ListView) findViewById(R.id.gamesListView);
//            gamesListView.setVisibility(View.INVISIBLE);
//
//            TextView noEntries = new TextView(this);
//            noEntries.setText("No entries");
//            gamesListView.addView(noEntries);
//        }

        //if there are entries, generate list
        //else {
            displayList();
        //}

        //set button onClick Listener
        setContentView(R.layout.game_list_layout);
        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGameClickHandler();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newGameClickHandler(){
        final EditText input = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("Name of Game")
                .setMessage("What is the Name of the Game?")
                .setView(input)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichbutton) {
                        String gameName = input.getText().toString();
                        addToGameList(gameName);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichbutton) {
                        //do nothing
                    }
                })
                .show();
        }

        public void addToGameList(String gameName){
            gameListGames.add(new Game(gameName));

            if(gameListGames.isEmpty()){
                list.add(gameListGames.get(0).getName());
            } else {
                list.add(gameListGames.get(gameListGames.size() - 1).getName());
            }

            displayList();
        }

        public void displayList(){
            //instantiate custom adapter
            GameArrayAdapter adapter = new GameArrayAdapter(list, this);

            //handle listview and assign adapter
            ListView lView = (ListView)findViewById(R.id.gamesListView);

            lView.setAdapter(adapter);
        }


    public static ArrayList<Game> getGameList() {
        return gameListGames;
    }
}


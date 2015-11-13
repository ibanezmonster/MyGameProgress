package com.example.greg.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ArrayList<Game> gameList;
    private ArrayList<Button> gameListButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_list_layout);
        Log.d("Starting", "...");

        MyGameProgress app = (MyGameProgress) getApplication();
        gameList = app.getAllGames();
        Log.d("Game list", "Got game list");

        gameList = new ArrayList<>();
        gameListButtons = new ArrayList<>();

        Game defaultGame = new Game("Default");
        gameList.add(defaultGame);

        //if no entries in game list, display a message stating that entries need to be added

        //if there are entries, generate list
        generateList(false);

        //list.add("item1");
        //list.add("item2");

        //set button onClick Listener
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
                        gameList.add(new Game(gameName));
                        generateList(true);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichbutton) {
                        //do nothing
                    }
                })
                .show();
        }


        public void generateList(boolean addGame){
            ArrayList<String> list = new ArrayList<String>();

            if(addGame){
                list.add(gameList.get(gameList.size() - 1).getName());
            }

            else {
                for (int i = 0; i < gameList.size(); i++) {
                    list.add(gameList.get(i).getName());
                }
            }
//            for (int i = 0; i < gameList.size(); i++) {
//                list.add(gameList.get(i).getName());
//            }

            //instantiate custom adapter
            GameArrayAdapter adapter = new GameArrayAdapter(list, this);

            //handle listview and assign adapter
            ListView lView = (ListView)findViewById(R.id.gamesListView);
            lView.setAdapter(adapter);
        }
    }

package team15.cs2110.virginia.edu.cs2110ghosthunter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class ScoresActivity extends ActionBarActivity {


    //    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    SharedPreferences.Editor prefEditor;
    SharedPreferences prefs;
    String HIGHSCORES;


    public static TextView textViewScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefEditor = prefs.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);


        Intent i = getIntent();

        TextView textView = (TextView) findViewById(R.id.highScore);
        if (i.getExtras() != null) {
            float passedScore = i.getExtras().getFloat("Score");
            textView.setText("Score: " +  passedScore);
        }


//            float prevScore;
//
//        if (i.getExtras() != null) {
//            float passedScore = i.getExtras().getFloat("Score");
//            float highScore = passedScore;
//
//
//
//            String HIGHSCORES = "high_scores";
//            String scoreString = Float.toString(highScore);
//            FileOutputStream outputStream;
//            try {
//                outputStream = openFileOutput(HIGHSCORES, Context.MODE_PRIVATE);
//                outputStream.write(scoreString.getBytes());
//                outputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            try {
//                FileInputStream fin = openFileInput("high_scores");
//                int c;
//                String tmp = "";
//                while ( (c = fin.read() )!= -1 ){
//                    tmp = tmp + Character.toString((char)c);
//                }
//                prevScore = Integer.parseInt(tmp);
//
////              textView.setText("High score: " + passedScore);
//                textView.setText("High Score: " + prevScore);
//
//                //int intScore = Integer.parseInt(passedScore);
//                if(passedScore >= prevScore || prevScore == 0){
//                    //Change to the high scores screen sending score with you
//
//
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//


//}
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
    }

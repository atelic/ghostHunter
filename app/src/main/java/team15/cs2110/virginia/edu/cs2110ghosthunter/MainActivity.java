package team15.cs2110.virginia.edu.cs2110ghosthunter;

import android.support.v7.app.ActionBarActivity;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.media.*;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.Backbeat);
//        mp.start();
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

    public void playButtonClicked(View button) {
        //Find the button by its id: playButton
        Button playButton = (Button)findViewById(R.id.playButton);

        //Set a listener, and link that on click to the Game Activity
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
    }

    public MainActivity() {
        super();
    }

    //Links the "View high scores button" to the high scores page
    public void scoresButtonClicked(View button) {
        //Find the button by its id: scoresButton
        Button scoreButton = (Button)findViewById(R.id.scoresButton);

        //Set a listener, and link that on click to the Scores Activity
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScoresActivity.class));
            }
        });
    }

}

package team15.cs2110.virginia.edu.cs2110ghosthunter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.util.Random;


public class GameActivity extends ActionBarActivity {

    Hello h;
    float f;
    float score;
    ImageView image;
    ImageView ghost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        image = (ImageView) this.findViewById(R.id.img);
    }

    /*
        D-PAD FUNCTIONALITY
     */
    public void upButtonClicked(View v){
        image.setX(image.getX());
        f = image.getY() - 20;
        Log.d("Up", "new position:" + f );
        image.setY(f);

    }

    public void downButtonClicked(View v){
        image.setX(image.getX());
        f = image.getY() + 20;
        Log.d("Down", "new position:" + f );
        image.setY(f);
    }

    public void rightButtonClicked(View v){
        image.setY(image.getY());
        f = image.getX() + 20;
        Log.d("Right", "new position: " + f);
        image.setX(f);
    }

    public void leftButtonClicked(View v){
        image.setY(image.getY());
        f = image.getX() - 20;
        Log.d("Left", "new position: " + f);
        image.setX(f);
    }

    public void makeGhost(){
        ghost = (ImageView) this.findViewById((R.id.ghost));

        Random rand = new Random();
        int xmin = 20;
        int xmax = 550;
        int ymin = 20;
        int ymax = 1100;

        int x = rand.nextInt((xmax - xmin) + 1) + xmin;

        int y = rand.nextInt((ymax - ymin) + 1) + ymin;

        ghost.setX(x);
        ghost.setY(y);
    }

    /*
     *  Should make a ghost disappear
     */
    public void killGhost(View v){

        ghost = (ImageView) this.findViewById((R.id.ghost));
        Log.d("Kill", "This should kill the ghost");

        this.score += 100;

        //This sends the Toast
        Context context = getApplicationContext();
        CharSequence text = "You killed a ghost. +100";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();

        //Move the ghost image
        makeGhost();
        TextView textView = (TextView) findViewById(R.id.score);

        //Update the score display
        textView.setText("Your score: " + this.score);
    }

}

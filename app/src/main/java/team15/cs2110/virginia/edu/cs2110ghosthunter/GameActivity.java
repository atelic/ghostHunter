package team15.cs2110.virginia.edu.cs2110ghosthunter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;


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

    /*
     *  Should make a ghost disappear
     */
    public void killGhost(View v){
        ghost = (ImageView) this.findViewById((R.id.ghost));

        ghost.setImageDrawable(null);
        ghost.setImageBitmap(null);
        ghost.setImageResource(android.R.color.transparent);

        Log.d("Kill", "This should kill the ghost");

        this.score += 100;
    }

}

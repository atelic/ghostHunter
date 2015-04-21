package team15.cs2110.virginia.edu.cs2110ghosthunter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.util.Random;


public class GameActivity extends ActionBarActivity {

    Hello h;
    float f;
    float f2;
    float score;
    float highScore = 0;
    public ImageView player;
    public ImageView ghost;
    public ImageView loot;
    public ImageView grenade;
    public Canvas canvas;
    private playerMovement user;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private boolean gameOver;
    int health;
    Random rand = new Random();
    //SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
    //SharedPreferences.Editor editor = prefs.edit();
    //TextView highScores = (TextView)findViewById(R.id.textView4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);


        //Set Highscores in ScoreView
        //highScores.setText("Highscore: " + prefs.getFloat("key",highScore));

        //5+ points for sound
        //Finds and starts music called beat_one.mp3 in /app/src/main/res/raw
        MediaPlayer mp = MediaPlayer.create(this, R.raw.beat_one);
        mp.start();

        //Grab the two images that start on the screen and assign to variables
        player = (ImageView) this.findViewById(R.id.img);
        user = new playerMovement(player, player.getX(), player.getY());
        ghost = (ImageView) this.findViewById((R.id.ghost));
        loot = (ImageView) this.findViewById(R.id.loot);
        grenade = (ImageView) this.findViewById((R.id.grenade));

        loot.setVisibility(View.INVISIBLE);
      //  health = (ImageView) this.findViewById(R.id.health);



        //Just started so the game can't be over
        //and start health at 100
        this.gameOver = false;
        this.health = 100;

    }

//    public void gameLoop(){
//        while(!gameOver){
//            float playerX = player.getX();
//            float playerY = player.getY();
//
//            int randX = rand.nextInt() * 5;
//            int randY = rand.nextInt() * 5;
//
//            float targetX = ghost.getX() + randX;
//            float targetY = ghost.getY() + randY;
//
//            this.ghost.setX(targetX);
//            this.ghost.setY(targetY);
//        }
//
//    }


    //7 points
    public void moveGhost(){
        int movementInt = (int ) (Math.random() * 40 + 1) ; //Number between 1 and 20
        int tmp = (int) ( Math.random() * 4 + 1); // will return 1 - 4
        if(tmp == 1)
            ghost.setY(ghost.getY() + movementInt);
        else if(tmp == 2)
            ghost.setY(ghost.getY() - movementInt);
        else if(tmp == 3)
            ghost.setY(ghost.getX() + movementInt);
        else if (tmp == 4)
            ghost.setY(ghost.getX() - movementInt);


    }

    /*
        D-PAD FUNCTIONALITY
     */
    public void upButtonClicked(View v){
        player.setX(player.getX());
        f = player.getY() - 20;
        Log.d("Up", "new position:" + f );
        player.setY(f);
        moveGhost();
   //     bombExplode(v);
        if(!dangerous()){
            proximity();
        }
    }

    public void downButtonClicked(View v){
        player.setX(player.getX());
        f = player.getY() + 20;
        Log.d("Down", "new position:" + f );
        player.setY(f);
        moveGhost();
   //     bombExplode(v);
        if(!dangerous()){
            proximity();
        }
    }

    public void rightButtonClicked(View v){
        player.setY(player.getY());
        f = player.getX() + 20;
        Log.d("Right", "new position: " + f);
        player.setX(f);
        moveGhost();
   //     bombExplode(v);
        if(!dangerous()){
            proximity();
        }
    }

    public void leftButtonClicked(View v){
        player.setY(player.getY());
        f = player.getX() - 20;
        Log.d("Left", "new position: " + f);
        player.setX(f);
        moveGhost();
   //     bombExplode(v);
        if(!dangerous()){
            proximity();
        }
    }

    //5 points
    //Toasts if close to ghost but doesn't hurt user
    public boolean proximity(){
        if(checkDist() <= 50 && checkDist() > 10){
            //This sends the Toast
            Context context = getApplicationContext();
            CharSequence text = "You're getting close to a ghost! Be careful";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
            return true;
        }
        else
            return false;
    }
    //5 points
    //getting too close to a ghost hurts the user
    //Also handles health system and game over stuff
    public boolean dangerous(){
        if(checkDist() <= 40){
            this.score -= 20;
            this.health -= 10;
            //Update the health display
            TextView textView = (TextView) findViewById(R.id.health);
            textView.setText("Your health: " + this.health);

            //If health goes under 0, game over and redirect to home page
            if(this.health <= 0){
                this.gameOver = true;
                Context context = getApplicationContext();
                CharSequence text = "You lost all your health, game over!";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();


                //Set Highscore
               /* if (this.score > this.highScore) {
                    this.highScore = this.score;
                    editor.putFloat("key", highScore);
                    editor.commit();

                }*/


                startActivity(new Intent(GameActivity.this, ScoresActivity.class));
            }

            //This sends the Toast
            Context context = getApplicationContext();
            CharSequence text = "Ouch! Too close to a ghost. -20";
            int duration = Toast.LENGTH_SHORT;

            Toast.makeText(context, text, duration).show();
            return true;
        }
        else{
            return false;
        }
    }


    public int checkDist(){
        float ghostX = ghost.getX();
        float ghostY = ghost.getY();

        float playerX = player.getX();
        float playerY = player.getY();

        int dist = (int) Math.sqrt(((playerX - ghostX) * (playerX - ghostX)) + ((playerY - ghostY) * (playerY - ghostY)));

        return dist;
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


        Log.d("Kill", "This should kill the ghost");

        this.score += 100;

        //This sends the Toast
        Context context = getApplicationContext();
        CharSequence text = "You killed a ghost. +100";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();

        //Move the ghost image
        makeGhost();

        //Update the score display
        TextView textView = (TextView) findViewById(R.id.score);
        textView.setText("Your score: " + this.score);


    }

    public void dropLoot(View v) {
        Log.d("Dead", "This should drop loot on the ghost's death");

         {
            int xLocation = (int) ghost.getX();
            int yLocation = (int) ghost.getY();

            loot.setX(xLocation);
            loot.setY(yLocation);
            loot.setVisibility(View.VISIBLE);
        }

    }

    public void collisionBox(View v, View y) {

    }

    public void placeBomb(View v){

        grenade.setVisibility(View.VISIBLE);

        Random rand2 = new Random();
        int xmin = 20;
        int xmax = 550;
        int ymin = 20;
        int ymax = 1100;

        float x2 = rand2.nextInt((xmax - xmin) + 1) + xmin;

        float y2 = rand2.nextInt((ymax - ymin) + 1) + ymin;

        grenade.setX(x2);
        grenade.setY(y2);
    }

    public void getRidOfBomb(View v) {
        grenade.setVisibility(View.INVISIBLE);
    }

    public int checkBombDist(){
        float ghostX = ghost.getX();
        float ghostY = ghost.getY();

        float grenadeX = grenade.getX();
        float grenadeY = grenade.getY();

        int dist2 = (int) Math.sqrt(((grenadeX - ghostX) * (grenadeX - ghostX)) + ((grenadeY - ghostY) * (grenadeY - ghostY)));

        return dist2;
    }

    public int detonate(View v){
        if (checkBombDist() <= 50) {
           killGhost(v);
           getRidOfBomb(v);
           return 1;
        } else {
           getRidOfBomb(v);
           return 0;
        }

    }



    //Saves health and score data between runs
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putFloat("Score", this.score);
        savedInstanceState.putInt("Health", this.health);
        // ... save more data
        super.onSaveInstanceState(savedInstanceState);
    }

    //Recovers the saved data
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.score = savedInstanceState.getFloat("Score");
        this.health = savedInstanceState.getInt("Health");
        // ... recover more data
    }
    
}

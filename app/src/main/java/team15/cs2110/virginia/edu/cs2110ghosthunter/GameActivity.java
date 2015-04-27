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
    float compareScore;
    float highScore = 0;
    public ImageView player;
    public ImageView ghost;
    public ImageView loot;
    public ImageView grenade;
    public ImageView watermelon;
    public Canvas canvas;
    private playerMovement user;
    private double x;
    private double y;
    private double vx;
    private double vy;
    private boolean gameOver;
    int health;
    Random rand = new Random();

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

        //Grab the images that start on the screen and assign to variables
        player = (ImageView) this.findViewById(R.id.img);
        user = new playerMovement(player, player.getX(), player.getY());
        ghost = (ImageView) this.findViewById((R.id.ghost));
        loot = (ImageView) this.findViewById(R.id.loot);
        loot.setVisibility(View.INVISIBLE);
        grenade = (ImageView) this.findViewById((R.id.grenade));
        watermelon = (ImageView) this.findViewById(R.id.watermelon);

        //Just started so the game can't be over
        //and start health at 100
        this.gameOver = false;
        this.health = 100;
        TextView textView = (TextView) findViewById(R.id.health);
        textView.setText("Your health: " + this.health);

    }


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
    int upClickCount = 0;
    public void upButtonClicked(View v){
        player.setX(player.getX());
        f = player.getY() - 40;
        Log.d("Up", "new position:" + f );
        player.setY(f);
        moveGhost();
        if(++upClickCount % 10 == 0){
            healthDrop();
        }
        if(!dangerous()){
            proximity();
        }
    }

    int downClickCount = 0;
    public void downButtonClicked(View v){
        player.setX(player.getX());
        f = player.getY() + 40;
        Log.d("Down", "new position:" + f );
        player.setY(f);
        moveGhost();
        if(++downClickCount % 10 == 0){
            healthDrop();
        }
        if(!dangerous()){
            proximity();
        }
    }
    int rightClickCount = 0;
    public void rightButtonClicked(View v){
        player.setY(player.getY());
        f = player.getX() + 40;
        Log.d("Right", "new position: " + f);
        player.setX(f);
        moveGhost();
        if(++rightClickCount % 10 == 0){
            healthDrop();
        }
        if(!dangerous()){
            proximity();
        }
    }

    int leftClickCount = 0;
    public void leftButtonClicked(View v){
        player.setY(player.getY());
        f = player.getX() - 40;
        Log.d("Left", "new position: " + f);
        player.setX(f);
        moveGhost();
        if(++leftClickCount % 10 == 0){
            healthDrop();
        }
        if(!dangerous()){
            proximity();
        }
    }

    //5 points
    //Toasts if close to ghost but doesn't hurt user
    public boolean proximity(){
        if(checkDist() <= 100 && checkDist() > 50){
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
        if(checkDist() <= 60){
            this.score -= 20;
            this.health -= 20;
            //Update the health display
            TextView textView = (TextView) findViewById(R.id.health);
            textView.setText("Your health: " + this.health);

            //If health goes under 0, game over and redirect to home page
            if(this.health <= 0){
                this.gameOver = true;

                //Toast that you lost
                Context context = getApplicationContext();
                CharSequence text = "You lost all your health, game over!";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration).show();

                //Change to the high scores screen sending score with you
                Intent i = new Intent(GameActivity.this , ScoresActivity.class);
                i.putExtra("Score", this.score);
                startActivity(i);


                //Get the high score view, and parse it to an int
                //Set Highscore
//                if (this.score > this.highScore) {
//                    this.highScore = this.score;
//                    TextView score = (TextView) findViewById(R.id.highScore);
//                    score.setText("Your health: " + this.score);
//
//                }
            }

            //This sends the Toast
            Context context = getApplicationContext();
            CharSequence text = "Ouch! Too close to a ghost. -10";
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
        dropLoot();
        //Move the ghost image
        makeGhost();

        //Update the score display
        TextView textView = (TextView) findViewById(R.id.score);
        textView.setText("Your score: " + this.score);


    }

    public void dropLoot() {
        Log.d("Dead", "This should drop loot on the ghost's death");

         {
            int xLocation = (int) ghost.getX();
            int yLocation = (int) ghost.getY();

            loot.setX(xLocation);
            loot.setY(yLocation);
            loot.setVisibility(View.VISIBLE);
        }

    }

    public void collectLoot(View v) {
        Log.d("Score", "You collect a Bonus!");

        loot.setVisibility(View.INVISIBLE);

        this.score += 200;

        TextView textView = (TextView) findViewById(R.id.score);
        textView.setText("Your score: " + this.score);
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


   //Health Drop
    public void healthDrop() {

        if (this.score % 200 == 0) {

            int xmin = 20;
            int xmax = 550;
            int ymin = 20;
            int ymax = 1100;

            int x = rand.nextInt((xmax - xmin) + 1) + xmin;

            int y = rand.nextInt((ymax - ymin) + 1) + ymin;

            watermelon.setX(x);
            watermelon.setY(y);

            watermelon.setVisibility(View.VISIBLE);
        }

    }

    //Health Pickup
    public void healthPickup(View view) {

        Log.d("Health", "Add 30 to health");

        this.health += 30;

        watermelon.setVisibility(View.INVISIBLE);

        TextView textView = (TextView) this.findViewById(R.id.health);
        textView.setText("Your Health: " + this.health);

        Context context = getApplicationContext();
        CharSequence text = "Bonus! Health +30";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();

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

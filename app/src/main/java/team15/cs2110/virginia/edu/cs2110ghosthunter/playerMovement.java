package team15.cs2110.virginia.edu.cs2110ghosthunter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.widget.ImageView;

/**
 * Created by Javan on 4/8/15.
 */
public class playerMovement {

        private double x;
        private double y;
        private double vx;
        private double vy;
        private Rect box;
        public ImageView image;

    public Rect getRectangle(ImageView image){
            int height = (int) (this.image.getHeight() * .5);
            int width = (int) (this.image.getWidth() * .5);

            this.box = new Rect((int)(this.x - (.5 * width)), (int)(this.y-(.5*height)),width,height);
            return box;
        }

        public void xLeft() {
            this.vx = this.vx - 12;
        }

        public void xRight(){
            this.vx = this.vx +12;
        }

        public void yUp() {
            this.vy = this.vy - 12;
        }

        public void yDown() {
            this.vy = this.vy + 12;
        }

        public void drag() {
            vx = this.vx - .2*(this.vx);
            vy = this.vy - .2*(this.vy);
        }

        public void move() {
            this.x += this.vx;
            this.y += this.vy;
        }

        public playerMovement(ImageView image, double x, double y){
            this.image = image;
            this.x = x;
            this.y = y;
            this.vx = 0;
            this.vy = 0;
        }

    }






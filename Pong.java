import java.awt.event.KeyEvent;

import com.sun.prism.paint.Stop;

public class Pong {
    public static void main(String[] args) {
        
        // set the scale of the coordinate system
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        //Change: stop the flickering of the paddle 
        StdDraw.enableDoubleBuffering();

        // initial values of ball
        double rx = 0.480, ry = 0.860;     // position
        double vx = 0.015, vy = 0.023;     // velocity
        double radius = 0.05;              // radius

        //initial values of paddle
        double[] px = {-0.2, 0.2, 0.2, -0.2};
        double[] py = {-0.85, -0.85, -.90, -0.90};
        double dpx = 0.018;                 // inc. paddle movement

        //Change b: initial values of box
        double bx = Math.random()*2-1;
        double by = Math.random();
        double rBox = 0.06;
        if(by == 1){
            by -= rBox;
        }
        if(bx == -1){
            bx += rBox;
        }else if(bx == 1){
            bx -= rBox;
        }

        //Change d: initial score
        int score = 0;

        boolean isGameOver = false;

        // main animation loop
        while (!isGameOver)  {
            //Change d: Stop game if ball hits floor
            if(ry + vy < -1.0 + radius){
                //isGameOver = true;
                break;
            }
            // bounce ball off wall according to law of elastic collision
            if (Math.abs(rx + vx) > 1.0 - radius) vx = -vx;
            if (Math.abs(ry + vy) > 1.0 - radius) vy = -vy;

            // update ball position
            rx = rx + vx;
            ry = ry + vy;

            // check if paddle moved
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && px[0]-dpx >= -1.0 )
                for (int i=0; i<4; i++)  px[i] -= dpx;
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && px[1]+dpx <= 1.0 )
                for (int i=0; i<4; i++)  px[i] += dpx;

            //Change a: Collision with paddle
            if(ry + vy <= py[0] && (rx + vx >= px[0] + radius && rx +vx <= px[1] + radius)){ 
                    ry = py[0] + radius;
                    vy = -vy;
            }

            //Change c: Randomize velocity of ball 
            if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
                vx = (Math.random()*2 -1)/15;
                vy = (Math.random()*2 -1)/15;
            }

            //Change b(and d): Collision with box
            if(ry + vy <= by + rBox + radius && ry + vy >= by - rBox - radius && rx + vx >= bx - rBox - radius&& rx + vx <= bx + rBox + radius){
                ry = by + rBox + radius;
                vy = -vy;
                bx = Math.random()*2 -1;
                by = Math.random();
                score++;
            }
            

            StdDraw.clear();

            // draw ball on the screen
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(rx, ry, radius);

            // draw paddle on the screen
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledPolygon(px, py);

            //Change b: draw box on the screen
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(bx, by, rBox);

            //Change d: draw scoreboard on screen
            StdDraw.textLeft(0.75, 0.95, "Score: " + score);

            // display and pause for 20 ms
            StdDraw.show();
            StdDraw.pause(20);
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0, 0, "Game Over!");
        // StdDraw.text(0, -0.08, "Press \"ESC\" to exit.");
        StdDraw.show();
        /*if(StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE)){
            System.exit(0);
        }*/
    }
}

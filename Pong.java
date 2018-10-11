import java.awt.event.KeyEvent;

public class Pong {
    public static void main(String[] args) {

        // set the scale of the coordinate system
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        // initial values of ball
        double rx = 0.480, ry = 0.860;     // position
        double vx = 0.015, vy = 0.023;     // velocity
        double radius = 0.05;              // radius

        //initial values of paddle
        double[] px = {-0.2, 0.2, 0.2, -0.2};
        double[] py = {-0.85, -0.85, -.90, -0.90};
        /*double px = 0;
        double py = -0.8;
        double length = 0.25;
        double width = 0.05; */
        double dpx = 0.018;                 // inc. paddle movement

        // main animation loop
        while (true)  {

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
            /*if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && px - dpx >= -1.0 + length){
                px -= dpx;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && px + dpx <= 1.0 - length){
                px += dpx;
            }*/

            //Paddle collision
            
            if(ry + vy <= py[0] && (rx + vx >= px[0] && rx +vx <= px[1])){ 
                    ry = py[0] + radius;
                    vy = -vy;
            }
                /*if(rx + vx >= px[0]){
                    //rx = px[0] + radius;
                    //vx = -vx;
                }else if(rx + vx <= px[1]){
                    //rx = px[1] + radius;
                    vx = -vx;
                }*/
            
            /*
            if(Math.abs(ry+vy) >= py + width + radius){
                ry = py + width + radius;
                vy = -vy;
            }*/

            StdDraw.clear();

            // draw ball on the screen
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(rx, ry, radius);

            // draw paddle on the screen
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledPolygon(px, py);
            //StdDraw.filledRectangle(px, py, length, width);

            // display and pause for 20 ms
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}
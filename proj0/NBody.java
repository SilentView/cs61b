/**
 * This class will have NO constructor.
 * The goal of this class is to simulate a universe specified in one of the data files.
 */

public class NBody {
    /**
     * Radius should be the second thing when .txt file
     * is separated by whitespace
     * @param file
     * @return
     */
    public static double readRadius(String file) {
        In in = new In(file);
        int garbage = in.readInt();
        return in.readDouble();
    }

    /**
     * Given a file name, it should return an array of Planets object
     * corresponding to the planets in the file
     */
    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int number = in.readInt();
        double radius = in.readDouble();
        Planet[] res = new Planet[number];
        for (int p_idx = 0 ; p_idx < number; p_idx++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String imgFile = in.readString();
            res[p_idx] = new Planet(xP, yP, xVel, yVel, mass, imgFile);
        }
        return res;
    }

    /**
     * get the initial state from a file, display the animation of duration T with discrete interval dt.
     * @param args
     * - args[0]: duration T
     * - args[1]: discrete interval dt
     * - args[2]: file path, the file containing initial state of the universe
     */
    public static void main(String[] args){
        //Store the 0th and 1st command line arguments as doubles named T and dt
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        //Store the 2nd command line argument as a String named filepath
        String filePath = args[2];

        //Read in the planets and the universe radius from the file described by filename
        Planet[] ps = readPlanets(filePath);
        //draw all the Planet on the starfield.jpg background
        double radius = readRadius(filePath);
        StdDraw.setScale(-radius, radius);


        //loop for animation
        StdDraw.enableDoubleBuffering();
        int num_p = ps.length;
        double cur_t = 0;
        while (cur_t < T) {
            //every frame should clear the offscreen canvas and redraw everything
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg", 2 * radius, 2* radius);

            double[] xForces = new double[num_p];
            double[] yForces = new double[num_p];

            //calculate the net x and y forces for each planet
            //to satisfy auto-grader, update synchronously
            for (int idx = 0; idx < num_p; idx++){
                xForces[idx] = ps[idx].calcNetForceExertedByX(ps);
                yForces[idx] = ps[idx].calcNetForceExertedByY(ps);
            }

            //update and draw on the offscreen canvas
            for (int idx = 0; idx < num_p; idx++){
                ps[idx].update(dt, xForces[idx], yForces[idx]);
                ps[idx].draw();
            }
            StdDraw.show();
            cur_t += dt;
            //10ms per frame
            StdDraw.pause(10);
        }
        //print out the final position at time T
        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                    ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
        }
    }
}

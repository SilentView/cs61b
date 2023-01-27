public class Planet {
    /**
     * current x position
     */
    public double xxPos;
    /**
     * current y position
     */
    public double yyPos;
    /**
     * current velocity in the x direction
     */
    public double xxVel;
    /**
     * current velicity in the y direction
     */
    public double yyVel;
    public double mass;
    /**
     * The name of the file that corresponds to the image 
     * that depicts the planet 
     */
    public String imgFileName;
    /**
     * the gravitational constant, estimated to be 6.67*10^-11
     * Note that this should be private
     */
    private static double G = 6.67 * 1e-11;
    /**
     * A Planet having coordinates, mass, velocity  and image file
     */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        //TODO
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
     * take in a Planet object and initialize an identical Planet object
     */
    public Planet(Planet p){
        //TODO
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * calculates the distance between two Planets
     */
    public double calcDistance(Planet p){
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) +
                         (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }

    /**
     * takes in a planet, and returns a double
     * describing the force exerted on this planet by the given planet
     */
    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        return G * mass * p.mass / (r * r);
    }

    /**
     *  calculate the force exerted in the X directions
     *  Note that this is
     */
    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    /**
     *  calculate the force exerted in the Y directions
     */
    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    /**
     * take in an array of Planets and calculate the net X force
     * exerted by all planets in that array upon the current Planet.
     * @param ps
     * @return forceX
     */
    public double calcNetForceExertedByX(Planet[] ps){
        double forceX = 0;
        for (Planet p : ps) {
            if (equals(p)) {
                continue;
            }
            forceX += calcForceExertedByX(p);
        }
        return forceX;
    }

    /**
     * take in an array of Planets and calculate the net Y force
     * exerted by all planets in that array upon the current Planet.
     * @param ps
     * @return forceY
     */
    public double calcNetForceExertedByY(Planet[] ps){
        double forceY = 0;
        for (Planet p : ps) {
            if (equals(p)) {
                continue;
            }
            forceY += calcForceExertedByY(p);
        }
        return forceY;
    }

    /**
     * update the velocity and position of the current planet using
     * a simplified method: assuming the acceleration is done at once
     * at the start of the short period.
     */
    public void update(double period, double forceX, double forceY){
        //update the velocity
        xxVel += period * forceX / mass;
        yyVel += period * forceY / mass;

        //update the position
        xxPos +=  period * xxVel;
        yyPos += period * yyVel;
    }

    /**
     * draw current Planet object at the correct position on the off-screen canvas
     *
     * Note that image file name has no higher level directory, so prefix
     * is needed to compose a path for the image file
     */
    public void draw(){
        String imgPath = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgPath);
    }
}

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return G * p.mass * mass / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        return (p.xxPos - xxPos) * calcForceExertedBy(p) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return (p.yyPos - yyPos) * calcForceExertedBy(p) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double Fx = 0;
        for (Planet p: planets) {
            if (!this.equals(p)) {
                Fx += (p.xxPos - xxPos) * calcForceExertedBy(p) / calcDistance(p);
            }
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double Fy = 0;
        for (Planet p: planets) {
            if (!this.equals(p)) {
                Fy += (p.yyPos - yyPos) * calcForceExertedBy(p) / calcDistance(p);
            }
        }
        return Fy;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;

        xxVel += ax * dt;
        yyVel += ay * dt;

        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" +imgFileName);
    }
}

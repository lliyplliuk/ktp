public class lab2 {
    public static void main(String[] args) {
        Point3d points[] = new Point3d[3];
        for (int i = 0; i < args.length; i = i + 3) {
            points[i / 3] = new Point3d(Double.parseDouble(args[i]), Double.parseDouble(args[i + 1]), Double.parseDouble(args[i + 2]));
        }
        for (int i = 0; i < points.length; i++) {
            System.out.println("point " + i + "(" + points[i].getX() + " ," + points[i].getY() + " ," + points[i].getZ() + ")");
        }
        if (points[0].equals(points[1]) || points[0].equals(points[2]) || points[1].equals(points[2])) {
            System.out.println("Two points is equals");
        } else {
            System.out.println("S=" + String.format("%.2f", computeArea(points[0], points[1], points[2])));
        }
    }

    public static double computeArea(Point3d a, Point3d b, Point3d c) {
        double ab = a.distanceTo(b);
        double bc = b.distanceTo(c);
        double ca = c.distanceTo(a);
        double p = (ab + bc + ca) / 2;
        return Math.sqrt(p * (p - ab) * (p - bc) * (p - ca));
    }
}

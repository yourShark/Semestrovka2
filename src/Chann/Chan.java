package Chann;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Comparator;


public class Chan {

    public static long polarOrder(Point p1, Point p2, Point p3) {
        return (p2.x() - p1.x()) * (long) (p3.y() - p1.y()) -
                (p2.y() - p1.y()) * (long) (p3.x() - p1.x());
    }


    public Point[] convex_hull(Point[] points) {

        if (points.length > 1) {
            int n = points.length;
            int k = 0;
            Point[] hull = new Point[2 * n];

            Arrays.sort(points, new Comparator<Point>() {
                public int compare(Point p1, Point p2) {
                    return p1.y() != p2.y() ? p1.y() - p2.y() : p1.x() - p2.x();
                }
            });


            // Builder lower hull
            for (int i = 0; i < n; i++) {
                while (k >= 2 && polarOrder(hull[k - 2], hull[k - 1], points[i]) <= 0) {
                    k--;
                }
                hull[k++] = points[i];
            }


            // Builder upper hull
            for (int i = n - 2, t = k + 1; i >= 0; i--) {
                while (k >= t && polarOrder(hull[k - 2], hull[k - 1], points[i]) <= 0) {
                    k--;
                }
                hull[k++] = points[i];
            }

            if (k > 1) {
                hull = Arrays.copyOfRange(hull, 0, k - 1);
            }
            return hull;

        } else if (points.length <= 1) {
            return points;
        } else {
            return null;
        }

    }

}

class Point {
    private int x;
    private int y;

    public Point(int i, int j) {
        x = i;
        y = j;

    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }
}
class main{
    public static void main(String[]args) {
        Chan a = new Chan();
        Point points[] = new Point[11];

        points[0] = new Point(0, 3);
        points[1] = new Point(2, 3);
        points[2] = new Point(1, 1);
        points[3] = new Point(2, 1);
        points[4] = new Point(3, 0);
        points[5] = new Point(0, 0);
        points[6] = new Point(3, 3);
        points[7] = new Point(1, 5);
        points[8] = new Point(4, 4);
        points[9] = new Point(3, 2);
        points[10] = new Point(5, 3);

        int k = points.length;
        System.out.println("================JARVIS================");
        a.convex_hull(points);
        System.out.println();

    }
}
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Stack;

class GrahamScan {
    private Stack<Point> hull = new Stack<Point>();

    public GrahamScan(Point[] pts) {
        int n = pts.length; //копия числа вершин
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = pts[i];
        }
        Arrays.sort(points);
        //сортируем по полярному углу
        Arrays.sort(points, 1, n, points[0].polarOrder());

        hull.push(points[0]);       // p[0] стартовая точка

        // найти индекс k1 первой точки, не равной points[0]
        int k1;
        for (k1 = 1; k1 < n; k1++)
            if (!points[0].equals(points[k1])) break;
        if (k1 == n) return;

        // найти индекс k2 первой точки, не коллинеарной с points[0] и points[k1]
        int k2;
        for (k2 = k1 + 1; k2 < n; k2++)
            if (Point.ccw(points[0], points[k1], points[k2]) != 0) break;
        hull.push(points[k2 - 1]);    // points[k2-1] вторая точка

        // алгоритм Грэхема
        for (int i = k2; i < n; i++) {
            Point top = hull.pop();
            while (Point.ccw(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }

        assert isConvex();
    }

    public GrahamScan() {

    }

    public Iterable<Point> hull() {
        Stack<Point> s = new Stack<Point>();
        for (Point p : hull) s.push(p);
        return s;
    }

    public int number() { //количество точек в готовом многоугольнике
        int n2 = 0;
        for (Point p : hull) ++n2;
        return n2;
    }

    //проверить, является ли граница многоугольника строго выпуклой
    private boolean isConvex() {
        int n = hull.size();
        if (n <= 2) return true;

        Point[] points = new Point[n];
        int k = 0;
        for (Point p : hull()) {
            points[k++] = p;
        }

        for (int i = 0; i < n; i++) {
            if (Point.ccw(points[i], points[(i + 1) % n], points[(i + 2) % n]) <= 0) {
                return false;
            }
        }
        return true;
    }

    //описание точки
    public static class Point implements Comparable<Point> {

        private final int x;    // x координата
        private final int y;    // y координата

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        public int compareTo(Point that) {
            if (this.y < that.y) return -1;
            if (this.y > that.y) return +1;
            if (this.x < that.x) return -1;
            if (this.x > that.x) return +1;
            return 0;
        }

        /**
         * Возвращает 1, если a→b→c - поворот против часовой стрелки
         */
        public static int ccw(Point a, Point b, Point c) {
            int area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
            if (area2 < 0) return -1;
            else if (area2 > 0) return +1;
            else return 0;
        }

        public Comparator<Point> polarOrder() {
            return new PolarOrder();
        }

        private class PolarOrder implements Comparator<Point> {
            public int compare(Point q1, Point q2) {
                int dx1 = q1.x - x;
                int dy1 = q1.y - y;
                int dx2 = q2.x - x;
                int dy2 = q2.y - y;
                if (dy1 >= 0 && dy2 < 0) return -1;    // q1 сверху; q2 снизу
                else if (dy2 >= 0 && dy1 < 0) return +1;    // q1 снизу; q2 сверху
                else if (dy1 == 0 && dy2 == 0) {            // коллинеарные
                    if (dx1 >= 0 && dx2 < 0) return -1;
                    else if (dx2 >= 0 && dx1 < 0) return +1;
                    else return 0;
                } else return -ccw(Point.this, q1, q2);     // обе сверху или снизу
            }
        }
    }

    public void start() {
        Random rand = new Random();
        int n = 20; //число вершин
        Point[] points = new Point[n];
        System.out.println("points: ");
        for (int i = 0; i < n; i++) {
            //координаты вершин
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);
            System.out.println("(" + x + ";" + y + ")");
            points[i] = new Point(x, y);
        }
        GrahamScan graham = new GrahamScan(points);
        System.out.println("------");
        if (n == graham.number()) {
            System.out.println("the hull exists");
        } else {
            System.out.println("there is no hull with ALL points");
            System.out.println("Possible hull:");
        }
        for (Point p : graham.hull()) {
            System.out.println("(" + p.x() + ";" + p.y() + ")");
        }
    }
}



class Graham {
    public static void main(String[] args) {
        GrahamScan g = new GrahamScan();
        g.start();
    }
}
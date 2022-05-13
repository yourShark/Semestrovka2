import java.util.*;



class Point {
    int x, y;
    Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}

class Jarvis { /* IronMam 😎 */
    // Найти ориентацию упорядоченного триплета (p, q, r).
    // Функция возвращает следующие значения
    // 0 -> p, q и r являются коллинеарными
    // 1 -> по часовой стрелке
    // 2 -> против часовой стрелки
    public static int orientation(Point p, Point q, Point r)
    {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

        if (val == 0)
            return 0;  // коллинеарен
        return (val > 0)? 1: 2; // часы или против часовой стрелки
    }

    // Печатает выпуклую оболочку из набора из n точек.
    public static void convexHull(Point points[], int n)
    {
        // Должно быть не менее 3 баллов

        if (n < 3) return;

        // Инициализировать результат

        Vector<Point> hull = new Vector<Point>();

        // Найти крайнюю левую точку
        int l = 0;
        for (int i = 1; i < n; i++)
            if (points[i].x < points[l].x)
                l = i;

        // Начинаем с крайней левой точки, продолжаем двигаться

        // против часовой стрелки до достижения начальной точки

        // опять таки. Этот цикл выполняется O (h) раз, где h

        // количество точек в результате или выходе.

        int p = l, q;

        do

        {
            hull.add(points[p]);

            q = (p + 1) % n;

            for (int i = 0; i < n; i++)

            {

                if (orientation(points[p], points[i], points[q]) == 2)
                    q = i;
            }

            p = q;
        } while (p != l);  // Пока мы не приходим к первому

        for (Point temp : hull)
            System.out.println("(" + temp.x + ", " + temp.y + ")");
    }

}
class main{
    public static void main(String[]args) {
        Jarvis a = new Jarvis();
        Point pointsk[] = new Point[11];

        pointsk[0] = new Point(0, 3);
        pointsk[1] = new Point(2, 3);
        pointsk[2] = new Point(1, 1);
        pointsk[3] = new Point(2, 1);
        pointsk[4] = new Point(3, 0);
        pointsk[5] = new Point(0, 0);
        pointsk[6] = new Point(3, 3);
        pointsk[7] = new Point(1, 5);
        pointsk[8] = new Point(4, 4);
        pointsk[9] = new Point(3, 2);
        pointsk[10] = new Point(5, 3);

        int k = pointsk.length;
        System.out.println("================JARVIS================");
        a.convexHull(pointsk, k);
        System.out.println();

    }
}
package ClosestPairOfPoints;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClosestPairOfPoints {

    public List<Point> insert(File F) throws FileNotFoundException {
        FileReader fr = new FileReader(F);
        BufferedReader br = new BufferedReader(fr);

        List<Point> points = new ArrayList<>(); // input String -> split(",") ArrayList

        try {
            String line = "";
            while((line = br.readLine()) != null){
                String[] p = line.split(",");
                points.add(new Point(Double.parseDouble(p[0]), Double.parseDouble(p[1])));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }
        return points;
    }

    public double closestPair(List<Point> arr, int l, int r) {

        int size = (r - l) + 1;

        if(size <= 3) {
            // Brute force
            return bruteForce(arr, l, r);
        }

        int mid = (l + r) / 2;

        /** left */
        double left = closestPair(arr, l, mid);
        /** right */
        double right = closestPair(arr, mid + 1, r);
        /** left, right 최솟값 return */
        double d = Math.min(left, right);

        /** n/2 지점에서 x좌표 값으로부터 d이내에 있는 좌표만 분리 */
        List<Point> window = new ArrayList<>();

        for(int i = l; i < r; i++) {
            double temp = arr.get(i).getX() - arr.get(mid).getX();
            if(temp * temp < d)
                window.add(arr.get(i));
        }

        /** 분리한 좌표를 y값을 기준으로 정렬 */
        window = sort(window, "y");

        /** window 내부의 최단거리를 계산 */
        int winSize = window.size();
        for(int i = 0; i < winSize - 1; i++) {
            for(int j = i + 1; j < winSize; j++) {
                double k = window.get(j).getY() - window.get(i).getY();
                if(k * k < d) {
                    double y_d = getDistance(window.get(i), window.get(j));
                    /** y값을 기준으로 d값보다 작은 거리에 있는 값들만 비교 */
                    if(y_d < d)
                        d = y_d;
                }
                else break; // 최소거리 d보다 큰 경우
            }
        }
        return d;
    }

    private double bruteForce(List<Point> arr, int l, int r) {
        /** 최소 거리 return */
        double result = Integer.MAX_VALUE;

        for(int i = l; i < r - 1; i++) {
            for(int j = i + 1; j < r; j++) {
                double dis = getDistance(arr.get(i), arr.get(j));
                if(dis < result)
                    result = dis;
            }
        }
        return result;
    }

    private double getDistance(Point p1, Point p2) {
        /** 두 점 사이 거리 구하기 */
        return Math.sqrt(Math.pow(Math.abs(p2.getX() - p1.getX()), 2)
                + Math.pow(Math.abs(p2.getY() - p1.getY()), 2));
    }

    public List<Point> sort(List<Point> arr, String str) {
        /** week 1과제인 삽입 정렬 사용하여 정렬 */
        for(int i = 1; i < arr.size(); i++) {   // 1번째 원소부터 끝까지 반복
            Point temp = arr.get(i);
            int j = i - 1;                  // temp의 전 원소
            if(str.equals("x")) {
                while(j >= 0 && temp.getX() < arr.get(j).getX()) {
                    arr.set(j + 1, arr.get(j)); // swap
                    j--;                        // j 1 감소
                }
            } else {
                while(j >= 0 && temp.getY() < arr.get(j).getY()) {
                    arr.set(j + 1, arr.get(j)); // swap
                    j--;                        // j 1 감소
                }
            }
            arr.set(j + 1, temp);
        }
        return arr;
    }

    public static class Point {
        /** 두개의 값을 return하기 위해 class 사용 */
        private double x;
        private double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

}

package ClosestPairOfPoints;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class TestClosestPairOfPoints {
    public static void main(String[] args) throws FileNotFoundException {
        ClosestPairOfPoints c = new ClosestPairOfPoints();

        File F = new File("data04_closest.txt");

        List<ClosestPairOfPoints.Point> arr = c.insert(F);

        /** 2차원 평면상의 좌표를 x값을 기준으로 정렬 */
        arr = c.sort(arr, "x");
        double result = c.closestPair(arr, 0, arr.size());

        System.out.println(String.format("%.3f", result));


    }
}

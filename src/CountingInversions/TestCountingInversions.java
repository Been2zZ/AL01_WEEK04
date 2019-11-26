package CountingInversions;

import java.io.File;
import java.io.FileNotFoundException;

public class TestCountingInversions {
    public static void main(String[] args) throws FileNotFoundException {

        CountingInversions c = new CountingInversions();

        File F = new File("data04_inversion.txt");

        int[] L = c.insert(F);

        System.out.println(c.sortAndCount(L, 0, L.length - 1).getNum());
    }
}

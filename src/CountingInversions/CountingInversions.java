package CountingInversions;

import java.io.*;
import java.util.Arrays;

public class CountingInversions {

    public int[] insert(File F) throws FileNotFoundException {
        FileReader fr = new FileReader(F);
        BufferedReader br = new BufferedReader(fr);

        String[] temp = null;

        try {
            String line = "";
            while((line = br.readLine()) != null){
                temp = line.split(",");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }

        /** String array -> Int array */
        int[] array = new int[temp.length];

        for(int i = 0; i < array.length; i++)
            array[i] = Integer.parseInt(temp[i]);

        return array;
    }

    public MyResult sortAndCount(int[] arr, int l, int r) {
        int count = 0;

        if(arr.length == 1) return new MyResult(0, arr); // return 0, arr
        else {
            int mid;
            if(l < r) {
                mid = (l + r) / 2;
                MyResult A = sortAndCount(arr, l, mid );
                count += A.getNum();
                MyResult B = sortAndCount(arr, mid + 1, r);
                count += B.getNum();
                MyResult L = mergeAndCount(arr, l, mid, r);
                count += L.getNum();
            }
            return new MyResult(count, arr);
        }
    }

    public MyResult mergeAndCount(int[] arr, int l, int mid, int r) {
        int count = 0;
        int i = 0, j = 0, k = l;

        /** A : left */
        int[] A = Arrays.copyOfRange(arr, l, mid + 1);
        /** B : right */
        int[] B = Arrays.copyOfRange(arr, mid + 1, r + 1);


        /** 정렬하며 Merge */
        while (i < A.length && j < B.length) {
            if (A[i] <= B[j])
                arr[k++] = A[i++];
            else {
                /** 역순 정렬인 경우 */
                arr[k++] = B[j++];
                count += (mid + 1) - (l + i);  // 역정렬인 경우, 뒤에 원소 값까지 count값에 같이 더해줌
            }
        }

        /** 남은 값 배열에 추가 */
        while (i < A.length)
            arr[k++] = A[i++];

        while (j < B.length)
            arr[k++] = B[j++];

        return new MyResult(count, arr);
    }

    final class MyResult {
        /** 두개의 값을 return하기 위해 final class 사용 */
        private final int num;
        private final int[] arr;

        MyResult(int num, int[] arr) {
            this.num = num;
            this.arr = arr;
        }

        public int getNum() {
            return num;
        }
    }
}

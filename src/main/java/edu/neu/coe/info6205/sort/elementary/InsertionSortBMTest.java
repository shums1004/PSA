package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.Arrays;

public class InsertionSortBMTest {

    public static void main(String[] args) {


        InsertionSort ins = new InsertionSort();
        int arraySize = 2000;
        Timer timer = new Timer();
        final int zzz = 20;

        for(int n = 0; n < 6; n++) {
            System.out.println("Loop: "+(n+1));

            arraySize = arraySize * 2;

           /*Code snippet for running benchmarks on insertion sort for randomly ordered array*/
            Integer[] ranArr = new Integer[arraySize];
            for (int i = 0; i < arraySize; i++)
                ranArr[i] =  ((int) (Math.random() * 100))*arraySize;

            final double ranTime = timer.repeat(20, () -> zzz, t -> {
                ins.sort(ranArr, 0, ranArr.length - 1);
                return null;
            });
            System.out.println("The insertion sort time of random number group with length "+arraySize+" is "+ranTime);
//            System.out.println("random " +ranTime);

            /*Code snippet for running benchmarks on insertion sort for sorted array*/
            Integer[] sortedArray = new Integer[arraySize];
            for (int i = 0; i < arraySize; i++)
                sortedArray[i] = ((int) (Math.random() * 100))*arraySize;

            Arrays.sort(sortedArray);
            final double sortTime = timer.repeat(20, () -> zzz, t -> {
                ins.sort(sortedArray, 0, sortedArray.length - 1);
                return null;
            });
            System.out.println("The insertion sort time of an ordered array of length "+arraySize+" is "+sortTime);
//            System.out.println("ordered " + sortTime);
            /*Code snippet for running benchmarks on insertion sort for partially ordered array*/
            Integer[] partiallySortedArray = new Integer[arraySize];
            for (int i = 0; i < arraySize / 2; i++)
                partiallySortedArray[i] = sortedArray[i];

            for (int i = arraySize / 2; i < arraySize; i++)
                partiallySortedArray[i] = ranArr[i];

            final double partTime = timer.repeat(20, () -> zzz, t -> {
                ins.sort(partiallySortedArray, 0, partiallySortedArray.length - 1);
                return null;
            });
            System.out.println("The insertion sort time of an partially-ordered  array of length "+arraySize+" is "+partTime);
//                System.out.println("partially "+partTime);
            /*Code snippet for running benchmarks on insertion sort for reverse ordered array*/
            Integer[] reverseSortedArray = new Integer[arraySize];
            for (int i = arraySize - 1; i >= 0; i--)
                reverseSortedArray[arraySize - i - 1] = sortedArray[i];

            final double reverseTime = timer.repeat(20, () -> zzz, t -> {
                ins.sort(reverseSortedArray, 0, reverseSortedArray.length - 1);
                return null;
            });
            System.out.println("The insertion sort time of an reverse-ordered  array of length "+arraySize+" is "+reverseTime);
//            System.out.println("Reverse "+reverseTime);
        }
    }
}

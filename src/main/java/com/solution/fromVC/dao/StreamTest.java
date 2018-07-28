package com.solution.fromVC.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;

public class StreamTest {

    public static void main(String[] args) {
//        List<String> list = Arrays.asList("picture", "Day", "act", "hit", "bag", "CAT", "word", "cat", "we", "act", "WELL", "switch", "eye");
//        String words = list.stream()
//                .filter(w -> w.length() < 4)
//                .map(String::toUpperCase)
//                .distinct()
//                .reduce("", String::concat);
//        System.out.println(words);
//        boolean ffs = IntStream.range(1, 10).anyMatch(x -> x == 9);
//        System.out.println(ffs);
        List<Integer> list = new ArrayList<>();
        List<Integer> arr = Arrays.asList(1, 4);
        Integer integer = arr.stream().max(Integer::compareTo).get();
        System.out.println(integer);
//        list.stream().max(Comparator.comparingInt(a -> a)).get();
//        Integer maxValue = (x, y) -> {
//            return Integer::compare;
//        };

        int max = list.stream().reduce(0, Integer::max);

        IntBinaryOperator run = (a, b) -> (a > b) ? a : b;

        IntBinaryOperator myLambda = (a, b)-> (a > b) ? a : b;

        IntBinaryOperator m = (x, y) -> (x % 2 == 0) ? x + 2 : x + 1;
        System.out.println(m.applyAsInt(4, 3));

//        IntBinaryOperator k = (x, y) -> (x == y) ? x + 2 : while(y > 0);
//        {
//            while (y > 0){
//                x = y * (y - 1);
//                y = y - 2;
//            }
//        }

        IntBinaryOperator f = (x, y) -> {
            if( x == y) {
                return x;
            }
//            while (y > 1){
//                x *= y * (y - 1);
//                y = y - 2;
//            }
//            return x;
                int k = 1;
                if(x != 1){
                    while (y > x){
                        k *= (y * (y - 1));
                        y = y - 2;
                        k = x;
                    }
                } else {
                    while (y > 1){
                        x *= y * (y - 1);
                        y = y - 2;
                    }
                }
            return x;
        };

        System.out.println(f.applyAsInt(3, 5));
    }



}

package com.happysathya.google;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinionWorkAssignment {

    public int[] solution(int[] data, int n) {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        Arrays.stream(data).forEach(i -> linkedHashMap.put(i, linkedHashMap.getOrDefault(i, 0) + 1));
        Stream.Builder<Integer> builder = Stream.builder();
        for (int i = 0; i < data.length; i++) {
            if (linkedHashMap.get(data[i]) <= n) {
                builder.add(data[i]);
            }
        }
        List<Integer> collect = builder.build().collect(Collectors.toList());
        int[] array = new int[collect.size()];
        for (int i = 0; i < collect.size(); i++) array[i] = collect.get(i);
        return array;
    }
}

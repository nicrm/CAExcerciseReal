/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author jens
 */
public class Streams {

    public static void main(String[] args) {
//        Stream.generate(() -> 2)
//                .map((n) -> n * 2)
//                .forEach((line) -> System.out.println(line));

//        Stream.of(2, 4, 5).forEach(System.out::println);
//        Consumer<String> consumer = (line) -> System.out.println(line);
//
//        InputStream input = new ByteArrayInputStream("hello\nworld".getBytes());
//        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//        Stream.generate(() -> {
//            try {
//                return reader.readLine();
//            } catch (IOException ex) {
//                return null;
//            }
//        })
//                .forEach(consumer);
//        final long start = System.currentTimeMillis();
//        Stream.generate(() -> 4)
//                .limit(100000000)
//                .map(i -> i / 2)
//                .map(i -> i + 5 * 3)
//                .parallel()
//                .forEach((input) -> {});
//        final long end = System.currentTimeMillis();
//        System.out.println((end - start));

    }

}

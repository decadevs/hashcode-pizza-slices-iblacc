package app;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) throws Exception {
        Path inputFile = Paths.get("input.txt");
        Path outputFile = Paths.get("output.txt");

        try {
            List<String> lines = Files.readAllLines(inputFile, StandardCharsets.UTF_8);
            Iterator<String> iterator = lines.iterator();
            int maxSlice = 0;
    
            StringTokenizer tokens = new StringTokenizer(iterator.next(), " ");
            if(tokens.hasMoreTokens()) {
                maxSlice = Integer.parseInt(tokens.nextToken());
            }

            tokens = new StringTokenizer(iterator.next(), " ");
            ArrayList<Integer> pizzaSlices = new ArrayList<>();

            while(tokens.hasMoreTokens()) {
                pizzaSlices.add(Integer.parseInt(tokens.nextToken()));
            }

            StringBuffer pizzaOutput1 = new StringBuffer();
            int possibleMax1 = 0, size = pizzaSlices.size(), currentVal = 0, count1 = 0;

            for(int i = 0; i < size ; i++) {
                currentVal = pizzaSlices.get(i);
                if(possibleMax1 + currentVal <= maxSlice) {
                    if(possibleMax1 != 0) pizzaOutput1.append(" ");
                    possibleMax1 += currentVal;
                    pizzaOutput1.append(i);
                    count1++;
                }

                if(possibleMax1 == maxSlice) {
                    break;
                }
            }

            StringBuffer pizzaOutput2 = new StringBuffer();
            int possibleMax2 = 0, count2 = 0;
            currentVal = 0;
            for(int i = size - 1; i >= 0 ; i--) {
                currentVal = pizzaSlices.get(i);
                if(possibleMax2 + currentVal <= maxSlice) {
                    if(possibleMax2 != 0) pizzaOutput2.append(" ");
                    possibleMax2 += currentVal;
                    pizzaOutput2.append(i);
                    count2++;
                }

                if(possibleMax2 == maxSlice) {
                    break;
                }
            }

            if(possibleMax1 > possibleMax2) {
                Files.write(outputFile, (count1 + "\n" + pizzaOutput1.reverse()).getBytes());
                return;
            }

            Files.write(outputFile, (count2 + "\n" + pizzaOutput2.reverse()).getBytes());  

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
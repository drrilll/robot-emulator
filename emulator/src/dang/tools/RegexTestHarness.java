package dang.tools;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestHarness {

    public static void main(String[] args) throws IOException{
    	InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        while (true) {

        	System.out.println("%nEnter your regex: ");
            Pattern pattern = 
            Pattern.compile(in.readLine());

            System.out.println("Enter input string to search: ");
            Matcher matcher = 
            pattern.matcher(in.readLine());

            boolean found = false;
            while (matcher.find()) {
                System.out.format("I found the text" +
                    " \"%s\" starting at " +
                    "index %d and ending at index %d.%n",
                    matcher.group(),
                    matcher.start(),
                    matcher.end());
                found = true;
            }
            if(!found){
                System.out.format("No match found.%n");
            }
        }
    }
}

import java.io.*;
import java.util.*;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        System.out.print("Enter File Name: ");
        String str= sc.nextLine();

        Scanner statscan= new Scanner(System.in); //System.in is a standard input stream
        System.out.print("Enter Stats File Name: ");
        String statName= statscan.nextLine();

        File file = new File(str);
        Map<String, Integer> wordCount = new TreeMap<String, Integer>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");


                for (int i = 0; i < words.length; i++) {
                    String x = String.valueOf(words[i]);
                    if (!wordCount.containsKey(x))
                        wordCount.put(x, 1);
                    else
                        wordCount.put(x, wordCount.get(x) + 1);

                }

            }


        } catch (FileNotFoundException e) {
            System.exit(1);
            e.printStackTrace();
        } catch (IOException e) {
            System.exit(1);
            e.printStackTrace();
        }
        File stat = new File(statName);


        try {
            boolean value = stat.createNewFile();
            if (value) {
                System.out.println("New Stats File created.");
            } else {
                System.out.println("File exists");
            }
        } catch (Exception e) {
            e.getStackTrace();
            System.exit(1);
        }
        try {
            FileWriter fileWriter = new FileWriter(statName);
            System.out.println("File writer was created");
            for (String word : wordCount.keySet()) {

                fileWriter.write(word + ":              " + wordCount.get(word)+ "\n");
            }
            fileWriter.close();

        }
        catch (IOException e) {
            e.getStackTrace();
            System.exit(1);
        }
        int count = 0;
        String maxword = " ";
        for (String word : wordCount.keySet())
            if(count < wordCount.get(word)) {
                maxword = word;
                count = wordCount.get(word);
            }
        System.out.println("read           " +wordCount.size() + " words");
        System.out.println("the word " + maxword + " appeared time(s) "+count);


    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static ArrayList<String> subtitles = new ArrayList<>();
    private static ArrayList<String> phrasalVerbs = new ArrayList<>();
    private static ArrayList<String> commonWords = new ArrayList<>();

    private static Set<String> foundPhrasalVerbs = new HashSet<>();
    private static Set<String> foundUncommonWords = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Hello World!");
        loadSubtitles();
        loadPhrasalVerbs();
        load5000List();
        printPhrasalVerbs();
        printUncommonWords();
    }

    private static void printUncommonWords() {
        System.out.println("we found following uncommon words");
        for (int i = 0; i < subtitles.size(); i++) {
            if (!commonWords.contains(subtitles.get(i))) {
                foundUncommonWords.add(subtitles.get(i));
            }
        }
        System.out.println(foundUncommonWords);
    }

    private static void printPhrasalVerbs() {
        System.out.println("we found following phrasal verbs");
        for (int i = 0; i < phrasalVerbs.size(); i++) {
            for (int j = 1; j < subtitles.size(); j++) {
                String inspect = subtitles.get(j-1) + " " + subtitles.get(j);
                if (inspect.contains(phrasalVerbs.get(i))) {
                    foundPhrasalVerbs.add(phrasalVerbs.get(i));
                }
            }
        }
        System.out.println(foundPhrasalVerbs);
    }

    private static void loadSubtitles() {
        System.out.println("Loading subtitles...");
        Scanner scan = null;
        try {
            scan = new Scanner(new File(("D:\\plan2\\input\\test_subtitles.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scan.hasNextLine()) {
            scan.nextLine();
            scan.nextLine();
            subtitles.addAll(Arrays.asList(scan.nextLine().replaceAll("[^a-zA-Z' ]", "").toLowerCase().split("\\s+")));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.isEmpty()) {
                    break;
                } else {
                    subtitles.addAll(Arrays.asList(line.replaceAll("[^a-zA-Z' ]", "").toLowerCase().split("\\s+")));
                }
            }
        }
    }

    private static void load5000List() {
        System.out.println("Loading 5000 list...");
        Scanner scan = null;
        try {
            scan = new Scanner(new File("D:\\plan2\\input\\free_list.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(",");
            commonWords.add(split[1]);
        }
    }

    private static void loadPhrasalVerbs() {
        System.out.println("Phrasal verbs loading..");
        Scanner scan = null;
        try {
            scan = new Scanner(new File("D:\\plan2\\input\\phrasal_verbs.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(",");
            phrasalVerbs.add(split[1].toLowerCase());
        }
    }
}

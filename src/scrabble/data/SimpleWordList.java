package scrabble.data;

import scrabble.util.Permutation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SimpleWordList implements WordList {

    private HashMap<String, String> scrabbleWords = new HashMap<>();
    private int size = 0;

    public static void main(String[] args) {
        SimpleWordList swl = new SimpleWordList();
        swl.initFromFile("wordlists/sowpods.txt");

        System.out.println("Please type your Word:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println(swl.validWordsUsingAllTiles(input).toString());

    }

    @Override
    public Set<String> validWordsUsingAllTiles(String tileRackPart) {
        HashSet<String> foundPerms = new HashSet<>();

        Permutation permToBeChecked = new Permutation(tileRackPart);
        for (Map.Entry<String, String> entry : scrabbleWords.entrySet()) {
            if (entry.getKey().equals(permToBeChecked.getNormalized())) {
                foundPerms.add(entry.getValue());
            }
        }

        return foundPerms;
    }

    @Override
    public Set<String> allValidWords(String tileRack) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean add(String word) {
        Permutation perm = new Permutation(word);
        if (word == null) {
            return false;
        } else {
            scrabbleWords.put(perm.getNormalized(), perm.getWord());
            size++;
            return true;
        }
    }

    @Override
    public boolean addAll(Collection<String> words) {
        if (words.size() == 0) {
            return false;
        } else {
            for (String word : words) {
                add(word);
            }
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public WordList initFromFile(String fileName) {
        BufferedReader br = null;
        String line;

        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("This file is not found!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is an IO error!");
        }
        return this;
    }
}

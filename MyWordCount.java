package Lap7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class MyWordCount {
	public static final String fileName = "src\\Lap7\\hamlet.txt";

	private List<String> words = new ArrayList<>();

	public MyWordCount() {
		try {
			this.words.addAll(Utils.loadWords(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public List<WordCount> getWordCounts() {
		List<WordCount> re = new ArrayList<>();
		for (String w : words) {
			WordCount wc = new WordCount(w, countWord(w));
			if (!re.contains(wc))
				re.add(wc);
		}
		return re;
	}

	public int countWord(String w) {
		int count = 0;
		for (int i = 0; i < words.size(); i++) {
			if (w.equals(words.get(i)))
				count++;

		}
		return count;
	}

	// Returns the words that their appearance are 1, do not consider duplidated
	// words
	public Set<String> getUniqueWords() {
		Set<String> re = new HashSet<>();
		List<WordCount> list = getWordCounts();
		for (WordCount wc : list) {
			if (wc.getCount() == 1)
				re.add(wc.getWord());
		}
		return re;
	}
	
	// Prints out the number of times each unique token appears in the file
		// data/hamlet.txt (or fit.txt) according ascending order of tokens
		// Example: An - 3, Bug - 10, ...
	public Set<WordCount> exportWordCounts() {
		Set<WordCount> re = new TreeSet<>(new Comparator<WordCount>() {
			public int compare(WordCount o1, WordCount o2) {
				if (o2.getCount() - o1.getCount() == 0)
					return o1.getWord().compareTo(o2.getWord());
				else
					return o1.getCount() - o2.getCount();

			}
		});
		for (String word : words) {
			WordCount wc = new WordCount(word, countWord(word));
			re.add(wc);
		}
		return re;
	}

	// Returns the words in the text file, duplicated words appear once in the
	// result
	public Set<String> getDistinctWords() {
		return new HashSet<>(words);
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according descending order of occurences
	// Example: Bug - 10, An - 3, Nam - 2.
	public Set<WordCount> exportWordCountsByOccurence() {
		Set<WordCount> re = new TreeSet<>(new Comparator<WordCount>() {
			@Override
			public int compare(WordCount o1, WordCount o2) {
				if (o2.getCount() - o1.getCount() == 0)
					return o2.getWord().compareTo(o1.getWord());
				else
					return o2.getCount() - o1.getCount();
			}
		});

		for (String word : words) {
			WordCount wc = new WordCount(word, countWord(word));
			re.add(wc);
		}

		return re;
	}

	// delete words begining with the given pattern (i.e., delete words begin with
	// 'A' letter
	public Set<WordCount> filterWords(String pattern) {
		Set<WordCount> re = new HashSet<>();
		for (String word : words) {
			WordCount wc = new WordCount(word, countWord(word));
			if (word.indexOf(pattern) != 0)
				re.add(wc);
		}
		return re;
	}

	public static void main(String[] args) {
		MyWordCount wc = new MyWordCount();

		System.out.println(wc.getWordCounts());
		System.out.println(wc.getUniqueWords());
		System.out.println(wc.getDistinctWords());
		System.out.println(Arrays.toString(wc.exportWordCounts().toArray()));
		System.out.println(Arrays.toString(wc.exportWordCountsByOccurence().toArray()));
		System.out.println(Arrays.toString(wc.filterWords("t").toArray()));
	}
}

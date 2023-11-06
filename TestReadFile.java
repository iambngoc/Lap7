package Lap7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestReadFile {
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner input = new Scanner(new File("src\\Lap7\\hamlet.txt"));
		Scanner input = new Scanner(new File("src\\Lap7\\fit.txt"));

		while (input.hasNext()) {
			String word = input.next();
			System.out.println(word);
		}
	}
}

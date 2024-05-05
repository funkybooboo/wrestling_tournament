package people.relationships;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Names {
    private static final List<String> schoolNamesList = new ArrayList<>();
    private static final List<String> personNamesList = new ArrayList<>();
    static {
        File nameFile = new File("src/txtFiles/Names");
        Scanner nameScanner;
        try {
            nameScanner = new Scanner(nameFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (nameScanner.hasNext()) {
            String personName = nameScanner.nextLine();
            personNamesList.add(personName);
        }

        File schoolFile = new File("src/txtFiles/SchoolsNames");
        Scanner schoolScanner = null;
        try {
            schoolScanner = new Scanner(schoolFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (schoolScanner.hasNext()) {
            String schoolName = schoolScanner.nextLine();
            schoolNamesList.add(schoolName);
        }
    }

    public static String getNextPersonName() {
        if (personNamesList.size() == 0) {
            throw new RuntimeException("Out of person names");
        }
        return personNamesList.remove(0);
    }

    public static String getNextSchoolName() {
        if (schoolNamesList.size() == 0) {
            throw new RuntimeException("Out of school names");
        }
        return schoolNamesList.remove(0);
    }

}

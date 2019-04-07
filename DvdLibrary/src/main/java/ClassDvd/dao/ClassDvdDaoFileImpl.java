package ClassDvd.dao;

import ClassDvd.dao.ClassDvdDao;
import ClassDvd.dto.Dvd;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ClassDvdDaoFileImpl implements ClassDvdDao {

    public static final String ROSTER_FILE = "dvd.txt";
    public static final String DELIMITER = "::";

    private Map<String, Dvd> dvds = new HashMap<>();

    public Dvd addDvd(String dvdTitle, Dvd newDvd)
            throws ClassDvdDaoException {
        newDvd = dvds.put(dvdTitle, newDvd);
        writeDvd();
        return newDvd;
        
    }

    @Override
    public ArrayList<Dvd> getAllDvd()
            throws ClassDvdDaoException {
        loadDvd();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String dvdTitle)
            throws ClassDvdDaoException {
        loadDvd();
        return dvds.get(dvdTitle);
    }


    @Override
    public Dvd editDvd(String dvdTitle) {
        // TODO
        return dvds.get(dvdTitle);
    }

    @Override
    public Dvd removeDvd(String dvdTitle)
            throws ClassDvdDaoException {
        Dvd removedDvd = dvds.remove(dvdTitle);
        writeDvd();
        return removedDvd;
    }

    @Override
    public Dvd searchDvd(String dvdTitle) {
        return dvds.get(dvdTitle);
    }

    private void loadDvd() throws ClassDvdDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new File(ROSTER_FILE));

        } catch (FileNotFoundException e) {
            throw new ClassDvdDaoException(
                    "-_- Could not load dvd data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            // splits the current line by :: 
            currentTokens = currentLine.split(DELIMITER);
            Dvd currentDvd = new Dvd(currentTokens[0]);

            currentDvd.setReleaseDate(currentTokens[1]);
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setDirectorName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserRating(currentTokens[5]);

            dvds.put(currentDvd.getDvdTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    private void writeDvd() throws ClassDvdDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new ClassDvdDaoException(
                    "Could not save student data.", e);
        }

        List<Dvd> dvdList = getAllDvd();
        for (Dvd currentDvd : dvdList) {

            out.println(currentDvd.getDvdTitle() + DELIMITER
                    + currentDvd.getReleaseDate() + DELIMITER
                    + currentDvd.getMpaaRating() + DELIMITER
                    + currentDvd.getDirectorName() + DELIMITER
                    + currentDvd.getStudio() + DELIMITER
                    + currentDvd.getUserRating());

            out.flush();
        }

        out.close();
    }

}

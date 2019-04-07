package ClassDvd.ui;
import ClassDvd.dto.Dvd;
import java.util.List;
public class ClassDvdView {

	UserIO io;

    public ClassDvdView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("  Main Menu  ");
        io.print(" ===========");
        io.print("1. List Dvd Title");
        io.print("2. Create New Dvd");
        io.print("3. View a Dvd");
        io.print("4. Remove a Dvd");
        io.print("5. edit a Dvd");
        io.print("6. search a Dvd");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices. ", 1, 7);
    }

    public Dvd getNewDvdInfo() { 
        String dvdTitle = io.readString("Please enter the new Dvd Title:"); 
        String releaseDate = io.readString("Please enter Release Date:");
        String mpaaRating = io.readString("Please enter Mpaa Rating:");
        String directorName = io.readString("Please enter Director Name:");
        String studio = io.readString("Please enter Studio:");
        String userRating = io.readString("Please enter user-Rating:");

        Dvd currentDvd = new Dvd(dvdTitle); 
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public void displayCreateDvdBanner() { 
        io.print("=== Create Student ===");

    }
    public void displayCreateSuccessBanner() { 
        io.readString("Dvd successfully created. Please hit enter to continue:");
        
        
    }

    public void displayDvdList(List<Dvd> dvdList) { 
        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getDvdTitle() + ": "
                    + currentDvd.getReleaseDate() + " "
                    + currentDvd.getMpaaRating() + " "
                    + currentDvd.getDirectorName() + " "
                    + currentDvd.getStudio() + " "
                    + currentDvd.getUserRating() + " "
                    
            		);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvd ===");
    }

    public void displayDisplayDvdBanner() {
        io.print("=== Edit dvd ===");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the Dvd Title.");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getDvdTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    
    public void displayEditDvdBanner()
    {
    	io.print("==== edit dvd === ");
    }
    
    public void displayEditSuccessBanner() {
        io.readString("Dvd successfully edit. Please hit enter to continue.");
    }
    
    public void displayRemoveDvdBanner() {
        io.print("=== Remove dvd ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Dvd successfully removed. Please hit enter to continue.");
    }
    
    public void displaySearchDvdBanner()
    {
    	io.print("search Dvd");
    }
    
    public void displaySearchSuccessBanner()
    {
    	io.readString("Dvd successfully search. Please hit enter to continue");
    }
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    	public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
	}

}

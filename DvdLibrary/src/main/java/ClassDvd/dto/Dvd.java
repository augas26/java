package ClassDvd.dto;

public class Dvd {

    private String dvdTitle;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating;
    
	public Dvd(String dvdTitle) {
		this.dvdTitle = dvdTitle;
	}
	public String getDvdTitle() {	
		return dvdTitle;
	}
    
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getMpaaRating() {
		return mpaaRating;
	}
	public void setMpaaRating(String mpaaRating) {
		this.mpaaRating = mpaaRating;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}
	public String getUserRating() {
		return userRating;
	}
	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	public void setDvdTitle(String string) {

		this.dvdTitle=dvdTitle;
		
	}
	
	
    
    
}

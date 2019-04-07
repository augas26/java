

package ClassDvd.dao;

import ClassDvd.dto.Dvd;

import java.util.ArrayList;
import java.util.List;


public interface ClassDvdDao {
    /**
	     * Adds the given title to the DVD and associates it with the given 
	     * student id. If there is already a student associated with the given 
	     * student id it will return that student object, otherwise it will 
	     * return null.
	     * 
	     * @param TITLE id with which student is to be associated
	     * @param DVD dvd to be added to the DVDs
	     * @return the DVD object previously associated with the given  
	     * student id if it exists, null otherwise
	     */
             Dvd addDvd(String title, Dvd dvd) throws ClassDvdDaoException;
             
             /**
	     * Returns a String array containing the dvds title of all 
	     * students in the roster.
	     * 
	     * @return String array containing the ids of all the students 
	     * in the roster
             * @throws ClassDvdDaoException 
	     */
             
             ArrayList<Dvd> getAllDvd() throws ClassDvdDaoException;
             
             /**
	     * Returns the dvd object associated with the given student id.
	     * Returns null if no such student exists
	     * 
	     * @param DvdTitle ID of the student to retrieve
	     * @return the Student object associated with the given student id,  
	     * null if no such student exists
	     */
             
             Dvd getDvd(String dvdTile) throws ClassDvdDaoException;
             /**
	     * Removes from the roster the student associated with the given id. 
	     * Returns the student object that is being removed or null if 
	     * there is no student associated with the given id
	     * 
	     * @param studentId id of student to be removed
	     * @return Student object that was removed or null if no student 
	     * was associated with the given student id
	     */
             Dvd removeDvd(String dvdTitle) throws ClassDvdDaoException;
             
             Dvd searchDvd(String dvdTitle)throws ClassDvdDaoException;
             
             Dvd editDvd(String dvdTitle)throws ClassDvdDaoException;
             
    

}

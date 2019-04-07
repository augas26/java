package ClassdvdController;

import ClassDvd.dao.ClassDvdDao;
import ClassDvd.dao.ClassDvdDaoException;
import ClassDvd.dao.ClassDvdDaoFileImpl;
import ClassDvd.dto.Dvd;
import ClassDvd.ui.ClassDvdView;
import ClassDvd.ui.UserIO;
import ClassDvd.ui.UserIOConsoleImpl;

import java.util.ArrayList;
import java.util.List;

public class ClassDvdController {

    ClassDvdView view;
    ClassDvdDao dao;

    public ClassDvdController(ClassDvdView v, ClassDvdDao d) {
        view = v;
        dao = d;
    }

    public void run() { // never throws exception
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = view.printMenuAndGetSelection();

                switch (menuSelection) {
                    case 1:
                        listDvd();
                        break;
                    case 2:
                        createDvd();
                        break;
                    case 3:
                        viewDvds();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 6:
                        searchDvd();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        view.displayUnknownCommandBanner();
                }

            }
            view.displayExitBanner();
        } catch (ClassDvdDaoException e) {
            view.displayErrorMessage(e.getMessage());

        }

    }

    private void createDvd() throws ClassDvdDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getDvdTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDvd() throws ClassDvdDaoException {
        view.displayDisplayAllBanner();
        ArrayList<Dvd> dvds = dao.getAllDvd();
        view.displayDvdList(dvds);

    }

    private void viewDvds() throws ClassDvdDaoException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(dvdTitle);
        view.displayDvd(dvd);
    }

    private void editDvd() throws ClassDvdDaoException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        dao.removeDvd(dvdTitle);
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getDvdTitle(), newDvd);
        view.displayEditSuccessBanner();
    }

    private void removeDvd() throws ClassDvdDaoException {
        view.displayRemoveDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        dao.removeDvd(dvdTitle);
        view.displayRemoveSuccessBanner();
    }

    private void searchDvd() throws ClassDvdDaoException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.searchDvd(dvdTitle);
        view.displayDvd(dvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}

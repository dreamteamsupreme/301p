package ca.ualberta.cmput301.t03.user;

import java.util.HashSet;

import ca.ualberta.cmput301.t03.Observable;
import ca.ualberta.cmput301.t03.Observer;
import ca.ualberta.cmput301.t03.datamanager.DataManager;
import ca.ualberta.cmput301.t03.inventory.BrowsableInventories;
import ca.ualberta.cmput301.t03.inventory.Inventory;

/**
 * Created by ross on 15-10-29.
 */
public class User implements Observable, Observer {
    private FriendsList friends;
    private UserProfile profile;
    private String username;
    private Inventory inventory;
    private BrowsableInventories browsableInventories;

    private DataManager dataManager;
    private HashSet<Observer> observers;

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update(this);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void update(Observable observable) {
        throw new UnsupportedOperationException();
    }
}

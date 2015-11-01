package ca.ualberta.cmput301.t03.user;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import ca.ualberta.cmput301.t03.Filter;
import ca.ualberta.cmput301.t03.Filterable;
import ca.ualberta.cmput301.t03.Observable;
import ca.ualberta.cmput301.t03.Observer;

/**
 * Created by ross on 15-10-29.
 */
public class FriendsList implements Observable, Filterable {

    public final static String type = "FriendsList";

    @Expose
    private Collection<User> friends; // should this be a list or a set?
    private HashSet<Observer> observers;

    public FriendsList() {
        friends = new HashSet<>();
        observers = new HashSet<>();
    }

    public Collection<User> getFriends() {
        return friends;
    }


    public void setFriends(Collection<User> friends) {
        this.friends = friends;
    }


    public void addFriend(User user) {
        friends.add(user);
    }

    public void removeFriend(User user) {
        friends.remove(user);
    }

    public void commitChanges() {
        notifyObservers();
    }

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
    public void addFilter(Filter filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeFilter(Filter filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearFilters() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getFilteredItems() {
        throw new UnsupportedOperationException();
    }
}

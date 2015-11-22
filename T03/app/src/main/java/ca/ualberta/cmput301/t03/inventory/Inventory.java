/*
 * Copyright (C) 2015 Kyle O'Shaughnessy, Ross Anderson, Michelle Mabuyo, John Slevinsky, Udey Rishi, Quentin Lautischer
 * Photography equipment trading application for CMPUT 301 at the University of Alberta.
 *
 * This file is part of {ApplicationName}
 *
 * {ApplicationName} is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ca.ualberta.cmput301.t03.inventory;

import com.google.gson.annotations.Expose;

import org.parceler.Parcel;
import org.parceler.Transient;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.UUID;

import ca.ualberta.cmput301.t03.Filter;
import ca.ualberta.cmput301.t03.Filterable;
import ca.ualberta.cmput301.t03.Observable;
import ca.ualberta.cmput301.t03.Observer;

/**
 * Inventory maintains a collection of items and will notify the observing user of any changes.
 * Represents the main model for the userinventory workflow and the browse inventories workflow.
 */
@Parcel
public class Inventory implements Filterable<Item>, Observable, Observer {
    public final static String type = "Inventory";
    @Expose
    private LinkedHashMap<UUID, Item> items;

    @Transient
    private HashSet<Observer> observers;

    /**
     * Should be constructed by the systems singleton or a User itself via the getInventory method,
     * and further managed by the data manager.
     *
     * Creates a new inventory that can be watched and added/removed to/from.
     */
    public Inventory() {
        observers = new HashSet<>();
        items = new LinkedHashMap<>();
    }

    /**
     * Get the hashmap of items.
     * Used by anyone needing to grab the inventory. Examples: BrowsingFriends, Browsing Self.
     * @return the hashmap of items
     */
    public HashMap<UUID, Item> getItems() {
        return items;
    }

    /**
     * Set the items, used by GSON and the data manager when loading the inventory.
     *
     * @param items items to be set
     */
    public void setItems(LinkedHashMap<UUID, Item> items) {
        this.items = items;
        for (Item item : items.values()) {
            item.addObserver(this);
        }
        notifyObservers();
    }

    /**
     * Get an Item from the inventory model.
     * Examples usages: Inspecting an Item, drawing items to ListView in inventory obtaining item data in a trade.
     *
     * @param itemUUID the item's UUID
     * @return the item
     */
    public Item getItem(UUID itemUUID) {
        return items.get(itemUUID);
    }

    /**
     * Get an Item from the inventory model.
     * Examples usages: Inspecting an Item, drawing items to ListView in inventory obtaining item data in a trade.
     *
     * @param itemUUID the item's string representation of the UUID
     * @return the item
     */
    public Item getItem(String itemUUID) {
        return items.get(UUID.fromString(itemUUID));
    }

    /**
     * Add an item to inventory.
     * Used during creation of items.
     * @param item item to add
     */
    public void addItem(Item item) {
        items.put(item.getUuid(), item);
        item.addObserver(this);
        notifyObservers();
    }

    /**
     * remove an item from inventory
     * User may delete items from inventory.
     * @param item item to remove
     */
    public void removeItem(Item item) {
        items.remove(item.getUuid());
        item.clearPhotoList();
        item.removeObserver(this);
        notifyObservers();
    }

    /**
     * alias for notifyObservers, call this after modifying the inventory.
     */
    public void commitChanges() {
        notifyObservers();
    }

    /**
     * {@inheritDoc}
     *
     * @param filter the filter you wish to apply
     */
    @Override
    public void addFilter(Filter filter) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     * @param filter the filter you wish to remove
     */
    @Override
    public void removeFilter(Filter filter) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearFilters() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getFilteredItems() {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param observer the Observer to add
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * {@inheritDoc}
     *
     * @param observer the Observer to remove
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void clearObservers() {
        observers.clear();
    }

    /**
     * {@inheritDoc}
     *
     * @param observable reference to the Observable that triggered the update()
     */
    @Override
    public void update(Observable observable) {

        notifyObservers();
    }

}

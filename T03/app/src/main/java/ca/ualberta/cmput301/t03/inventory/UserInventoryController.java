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

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ca.ualberta.cmput301.t03.configuration.Configuration;
import ca.ualberta.cmput301.t03.datamanager.DataManager;
import ca.ualberta.cmput301.t03.datamanager.HttpDataManager;
import ca.ualberta.cmput301.t03.inventory.AddItemView;
import ca.ualberta.cmput301.t03.inventory.EditItemView;
import ca.ualberta.cmput301.t03.inventory.Inventory;
import ca.ualberta.cmput301.t03.inventory.Item;
/**
 * Copyright 2015 Quentin Lautischer
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class UserInventoryController {
    public final static String ITEM_NAME = null;

    Inventory inventory;
    Context context;
    Configuration configuration;
    DataManager dataManager;

    public UserInventoryController(Context context, Inventory inventory) {
        this.inventory = inventory;
        this.context = context;

        this.configuration = new Configuration(context);
        this.dataManager = new HttpDataManager(context);
    }

    public void addItemButtonClicked() {
//        throw new UnsupportedOperationException();
        Intent intent = new Intent(context, AddItemView.class);
        context.startActivity(intent);
    }

    public void addingItemToInventory(Item item) {
        inventory.addItem(item);
    }

    public void inspectItem(Item item){
        Intent intent = new Intent(context, EditItemView.class);
        intent.putExtra("ITEM_UUID", item.getUuid().toString());
        context.startActivity(intent);
    }

}
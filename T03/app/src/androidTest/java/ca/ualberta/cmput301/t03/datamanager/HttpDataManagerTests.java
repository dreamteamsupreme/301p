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

package ca.ualberta.cmput301.t03.datamanager;

import ca.ualberta.cmput301.t03.datamanager.mocks.MockNetworkUtil;

/**
 * Created by rishi on 15-10-30.
 */
public class HttpDataManagerTests extends BaseDataManagerTests<HttpDataManager> implements DataManagerApiTests {

    @Override
    protected HttpDataManager createNewDataManager() {
        return new HttpDataManager();
    }

    @Override
    public void testKeyExists() {
        super.keyExistsTest();
    }

    @Override
    public void testGetDataWhenKeyDoesNotExistThrowsException() {
        super.getDataWhenKeyDoesNotExistThrowsExceptionTest();
    }

    @Override
    public void testWriteData() {
        super.writeDataTest();
    }

    @Override
    public void testDelete() {
        super.deleteTest();
    }

    @Override
    public void testIsOperational() {
        super.isOperationalTest();
    }

    public void testIsOperationalWhenNetworkOff() {
        MockNetworkUtil mockNetworkUtil = new MockNetworkUtil();
        HttpDataManager testDataManager = new HttpDataManager(mockNetworkUtil);
        assertTrue(testDataManager.isOperational());

        mockNetworkUtil.setNetworkState(false);
        assertFalse(testDataManager.isOperational());
    }
}

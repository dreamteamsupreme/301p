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

package ca.ualberta.cmput301.t03.datamanager.jobs;


import java.io.IOException;

import ca.ualberta.cmput301.t03.datamanager.DataKey;
import ca.ualberta.cmput301.t03.datamanager.elasticsearch.ElasticSearchHelper;

/**
 * A {@link DataManagerJob} for performing the delete request.
 * Created by rishi on 15-11-11.
 */
public class DeleteDataJob extends DataManagerJob {

    /**
     * Creates an instance of {@link DeleteDataJob}/
     * @param dataKey The {@link DataKey} pointing to the object to be deleted.
     */
    public DeleteDataJob(DataKey dataKey) {
        super(dataKey);
    }

    /**
     * Calls the {@link ElasticSearchHelper#sendDeleteRequestAtPath(String)} operation using the
     * {@link DataKey} passed during construction.
     * @throws IOException Thrown if the network request fails.
     */
    @Override
    public void onRun() throws IOException {
        new ElasticSearchHelper().sendDeleteRequestAtPath(getRequestSuffix());
    }
}

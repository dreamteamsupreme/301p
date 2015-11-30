/*
 * Copyright (C) 2015 Kyle O'Shaughnessy, Ross Anderson, Michelle Mabuyo, John Slevinsky, Udey Rishi, Quentin Lautischer
 * Photography equipment trading application for CMPUT 301 at the University of Alberta.
 *
 * This file is part of "Trading Post"
 *
 * "Trading Post" is free software: you can redistribute it and/or modify
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

package ca.ualberta.cmput301.t03.common.exceptions;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

import ca.ualberta.cmput301.t03.TradeApp;
import ca.ualberta.cmput301.t03.trading.Trade;

/**
 * Utility methods for {@link Exception}.
 * Created by rishi on 15-10-30.
 */
public class ExceptionUtils {

    // Source: http://stackoverflow.com/questions/1149703/how-can-i-convert-a-stack-trace-to-a-string
    // Date: 30 Oct, 2015

    /**
     * Extracts the stack trace from an {@link Exception} object to a formatted {@link String}.
     *
     * @param exception The {@link Exception} whose stack trace has to be extracted out.
     * @return The extracted stack trace {@link String}.
     */
    public static String getStackTrace(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String stackTrace = sw.toString();
        pw.close();
        return stackTrace;
    }

    private static void toast(String message, int duration) {
        Toast.makeText(TradeApp.getContext(), message, duration).show();
    }

    public static void toastLong(String message) {
        toast(message, Toast.LENGTH_LONG);
    }

    public static void toastShort(String message) {
        toast(message, Toast.LENGTH_SHORT);
    }

    public static void toastOfflineWarning() {
        toastLong("Functionality not available in offline mode");
    }

    public static void toastErrorWithNetwork() {
        toastLong("There was an error connecting to the network");
    }

    public static void toastThingNotFound(String thing) {
        toastLong("Could not find " + thing);
    }


}

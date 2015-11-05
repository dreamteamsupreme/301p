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

package ca.ualberta.cmput301.t03.trading;

import ca.ualberta.cmput301.t03.trading.exceptions.IllegalTradeStateTransition;

/**
 * Created by ross on 15-10-29.
 */
public class TradeStateDeclined implements TradeState {
    @Override
    public Boolean isClosed() {
        return Boolean.TRUE;
    }

    @Override
    public Boolean isOpen() {
        return !isClosed();
    }

    @Override
    public Boolean isEditable() {
        return Boolean.FALSE;
    }

    @Override
    public void offer(Trade trade) throws IllegalTradeStateTransition {
        throw new IllegalTradeStateTransition("Declined trade cannot be offered");
    }

    @Override
    public void cancel(Trade trade) throws IllegalTradeStateTransition {
        throw new IllegalTradeStateTransition("Declined trade cannot be cancelled");
    }

    @Override
    public void accept(Trade trade) throws IllegalTradeStateTransition {
        throw new IllegalTradeStateTransition("Declined trade cannot be accepted");
    }

    @Override
    public void decline(Trade trade) throws IllegalTradeStateTransition {
        throw new IllegalTradeStateTransition("Declined trade cannot be declined");
    }

    @Override
    public String toString() {
        return "TradeStateDeclined";
    }

}

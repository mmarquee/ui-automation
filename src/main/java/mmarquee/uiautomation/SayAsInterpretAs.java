/*
 * Copyright 2017 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mmarquee.uiautomation;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the values that indicate how a text-to-speech engine should
 * interpret specific data.
 */
public enum SayAsInterpretAs {
    /**
     * No value.
     */
    None(0),

    /**
     * Spell.
     */
    Spell(1),

    /**
     * Cardinal value.
     */
    Cardinal(2),

    /**
     * Ordinal.
     */
    Ordinal(3),

    /**
     * Number.
     */
    Number(4),

    /**
     * Date.
     */
    Date(5),

    /**
     * Time value.
     */
    Time(6),

    /**
     * Telephone.
     */
    Telephone(7),

    /**
     * Currency value.
     */
    Currency(8),

    /**
     * Net.
     */
    Net(9),

    /**
     * Url.
     */
    Url(10),

    /**
     * Address.
     */
    Address(11),

    /**
     * Alphanumeric value.
     */
    Alphanumeric(12),

    /**
     * Name.
     */
    Name(13),

    /**
     * Media.
     */
    Media(14),

    /**
     * Date - Month/Day/Year value.
     */
    Date_MonthDayYear(15),

    /**
     * Date - Day/Month/Year value.
     */
    Date_DayMonthYear(16),

    /**
     * Date - Year/Month/Day value.
     */
    Date_YearMonthDay(17),

    /**
     * Date - Year/Month value.
     */
    Date_YearMonth(18),

    /**
     * Date - Month/Year value.
     */
    Date_MonthYear(19),

    /**
     * Date - Day/Month value.
     */
    Date_DayMonth(20),

    /**
     * Date - Month/Day value.
     */
    Date_MonthDay(21),

    /**
     * Date - Year value.
     */
    Date_Year(22),

    /**
     * Time - Hours, Minutes, Seconds (12hr).
     */
    Time_HoursMinutesSeconds12(23),

    /**
     * Time - Hours, Minutes (12hr).
     */
    Time_HoursMinutes12(24),

    /**
     * Time - Hours, Minutes, Seconds (24hr).
     */
    Time_HoursMinutesSeconds24(25),

    /**
     * Time - Hours, Minutes (24hr).
     */
    Time_HoursMinutes24(26);

    /**
     * The value.
     */
    private int value;

    /**
     * Gets the value.
     * @return The value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for SayAsInterpretAs.
     * @param inValue The value.
     */
    SayAsInterpretAs(final int inValue) {
        this.value = inValue;
    }

    /**
     * Map of integers to values.
     */
    private static final Map<Integer, SayAsInterpretAs> INT_TO_TYPE_MAP =
            new HashMap<>();
    static {
        for (SayAsInterpretAs type : SayAsInterpretAs.values()) {
            INT_TO_TYPE_MAP.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer.
     *
     * @param value The given integer
     * @return The value (as an SayAsInterpretAs)
     */
    public static SayAsInterpretAs fromInt(final int value) {
        SayAsInterpretAs type = INT_TO_TYPE_MAP.get(value);
        if (type == null) {
            return SayAsInterpretAs.None;
        }
        return type;
    }
}

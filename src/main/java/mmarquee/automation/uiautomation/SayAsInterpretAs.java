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
package mmarquee.automation.uiautomation;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the values that indicate how a text-to-speech engine should interpret specific data.
 */
public enum SayAsInterpretAs {
    None(0),
    Spell(1),
    Cardinal(2),
    Ordinal(3),
    Number(4),
    Date(5),
    Time(6),
    Telephone(7),
    Currency(8),
    Net(9),
    Url(10),
    Address(11),
    Alphanumeric(12),
    Name(13),
    Media(14),
    Date_MonthDayYear(15),
    Date_DayMonthYear(16),
    Date_YearMonthDay(17),
    Date_YearMonth(18),
    Date_MonthYear(19),
    Date_DayMonth(20),
    Date_MonthDay(21),
    Date_Year(22),
    Time_HoursMinutesSeconds12(23),
    Time_HoursMinutes12(24),
    Time_HoursMinutesSeconds24(25),
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
    SayAsInterpretAs (final int inValue) {
        this.value = inValue;
    }

    private static final Map<Integer, SayAsInterpretAs> intToTypeMap = new HashMap<>();
    static {
        for (SayAsInterpretAs type : SayAsInterpretAs.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    /**
     * Gets the enumeration from the given integer.
     *
     * @param value The given integer
     * @return The value (as an SayAsInterpretAs)
     */
    public static SayAsInterpretAs fromInt(final int value) {
        SayAsInterpretAs type = intToTypeMap.get(value);
        if (type == null) {
            return SayAsInterpretAs.None;
        }
        return type;
    }
}

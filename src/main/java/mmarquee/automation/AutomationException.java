/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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
package mmarquee.automation;

/**
 * Created by Mark Humphreys on 14/07/2016.
 *
 * An exception thrown when there is an error returned by the underlying automation library
 */
public class AutomationException extends Exception {

    private static final long serialVersionUID = -3074432505437414359L;

    final int errorcode;

    public AutomationException(String message)
    {
        super(message);
        this.errorcode = 0;
    }

    public AutomationException(int errorcode)
    {
        super(createErrorString(errorcode));
        this.errorcode = errorcode;
    }

    private static String createErrorString(int errorcode)
    {
        return "Error: 0x" + Integer.toHexString(errorcode);
    }

    /**
     * Returns the error code, if available, 0 otherwise
     * <p>
     * For Microsoft error codes, see:
     * <ul>
     * <li>https://msdn.microsoft.com/de-de/library/windows/desktop/ee671218(v=vs.85).aspx
     * <li>https://support.symantec.com/en_US/article.TECH12638.html
     * </ul>
     *
     * @return the errorcode
     */
    public int getErrorcode() {
        return errorcode;
    }
}

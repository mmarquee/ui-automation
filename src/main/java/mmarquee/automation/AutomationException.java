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
 * An exception thrown when there is an error returned by the underlying
 * automation library.
 *
 * @author Mark Humphreys
 * Date 14/07/2016.
 */
public class AutomationException extends Exception {
	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -3074432505437414359L;

	/**
	 * The error code.
	 */
	private final int errorCode;

	/**
	 * Constructor for the AutomationException.
	 *
	 * @param message The message
	 */
	public AutomationException(final String message) {
		super(message);
		this.errorCode = 0;
	}

	/**
	 * Constructor for the AutomationException.
	 *
	 * @param inErrorCode The error code
	 */
	public AutomationException(final int inErrorCode) {
		super(createErrorString(inErrorCode));
		this.errorCode = inErrorCode;
	}

	/**
	 * Constructor for the AutomationException.
	 *
	 * @param cause The underlying exception which caused this
	 */
	public AutomationException(final Throwable cause) {
		super(cause);
		this.errorCode = 0;
	}

	/**
	 * Creates the error string.
	 *
	 * @param inErrorCode The error code to use.
	 * @return The formatted error message.
	 */
	private static String createErrorString(final int inErrorCode) {
		return "Error: 0x" + Integer.toHexString(inErrorCode);
	}

	/**
	 * Returns the error code, if available, 0 otherwise.
	 * <p>
	 * For Microsoft error codes, see:
	 * <ul>
	 * <li>https://msdn.microsoft.com/de-de/library/windows/desktop/ee671218(v=vs.85).aspx
	 * <li>https://support.symantec.com/en_US/article.TECH12638.html
	 * </ul>
	 *
	 * @return the error code.
	 */
	public final int getErrorcode() {
		return errorCode;
	}
}

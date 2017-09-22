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

import static org.junit.Assert.*;

import org.junit.Test;

/**
 
 * Tests for the ControlType enum behaviour.
 */
public class ControlTypeTest {
	
	@Test
	public void getValue() throws Exception {
		int value = ControlType.HeaderItem.getValue();
		assertEquals(50035,value);
	}

	@Test
	public void getValue_Min() throws Exception {
		int value = ControlType.None.getValue();
		assertEquals(0,value);
	}
	
	@Test
	public void fromValue() throws Exception {
		ControlType type = ControlType.fromValue(50035);
		assertEquals(ControlType.HeaderItem, type);
	}
	
	@Test
	public void fromValue_Null() throws Exception {
		ControlType type = ControlType.fromValue(0);
		assertEquals(ControlType.None, type);
	}
	
	@Test
	public void fromValue_Invalid() throws Exception {
		ControlType type = ControlType.fromValue(-123);
		assertEquals(ControlType.None, type);
	}
}
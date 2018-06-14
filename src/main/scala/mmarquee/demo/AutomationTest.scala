/*
 * Copyright 2017-18 inpwtepydjuf@gmail.com
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

package mmarquee.demo

object AutomationTest {

  var notepad: NotepadApplication = _

  def start(): Unit = {
    notepad = new NotepadApplication()

    notepad.launch()
    notepad.addText("Hello there")
    notepad.clickExit()
    val confirm = notepad.getConfirm

    if (confirm != null) {
      System.out.println(s"Found the confirmation popup")
    } else {
      System.out.println(s"Didn't find confirmation window")
    }

    notepad.confirmExit()
  }

  def main(args: Array[String]) {
    start()
  }
}

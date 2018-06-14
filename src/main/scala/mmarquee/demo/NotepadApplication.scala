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

import mmarquee.automation.controls.menu.AutomationMenuItem
import mmarquee.automation.controls.{AutomationWindow, Search}

class NotepadApplication extends BaseApplication {
  private var window: AutomationWindow = _

  def launch(): Unit = {
    application = automation.launch("notepad.exe")

    // A short wait for the expand to work, just in case
    Thread.sleep(500)

    // Find the window
    window = automation.getDesktopWindow("Untitled - Notepad")
  }

  def addText(tag: String): Unit = {
    val edit = window.getEditBox(Search.getBuilder(0).build())

    edit.setValue(tag)
  }

  def clickExit(): Unit = {
    val menu = window.getMainMenu(1)
    val exit: AutomationMenuItem = menu.getMenuItem("File", "Exit")
    exit.click()
  }

  def confirmExit(): Unit = {
    val confirm = window.getWindow(Search.getBuilder("Notepad").build())
    val button = confirm.getButton(Search.getBuilder("Don't Save").build())
    button.click()
  }

  def getConfirm: AutomationWindow = {
    window.getWindow(Search.getBuilder("Notepad").build())
  }

  def findProcess: Boolean = {
    automation.findProcess("notepad.exe") != null
  }
}

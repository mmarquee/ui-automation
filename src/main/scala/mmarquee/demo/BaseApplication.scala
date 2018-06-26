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

package mmarquee.demo

import com.sun.jna.platform.win32.{User32, WinDef}
import mmarquee.automation.UIAutomation
import mmarquee.automation.controls.{AutomationApplication, AutomationWindow}

import scala.util.{Failure, Success, Try}

trait BaseApplication {
  val automation = UIAutomation.getInstance
  var application: AutomationApplication = _

  var applicationName = ""
  var applicationArguments = ""
  var applicationTitle = ""

  def start(): Unit = {
    application = automation.launchOrAttach(this.applicationName, this.applicationArguments)
    application.waitForInputIdle(500000)
  }

  def exit(): Unit = {
    if (this.application != null) {
      println(s"quiting ${this.applicationTitle}")

      val hwnd: WinDef.HWND = User32.INSTANCE.FindWindow(null, this.applicationTitle)
      println("hwnd=" + hwnd)

      this.application.close(this.applicationTitle)
      Thread.sleep(500)
      this.application.quit(this.applicationTitle)
    }
  }

  def getDesktopWindow: Try[AutomationWindow] = {
    System.out.println(s"Looking for $applicationTitle")
    Try(automation.getDesktopWindow(applicationTitle, 3))
  }

  def isRunning: Boolean = {
    // Need to wait for a couple or more of seconds to make sure it's started
    Thread.sleep(4000)

    getDesktopWindow match {
      case Success(window) => true
      case Failure(window) => false
    }
  }

  def end: Unit = {
    this.application.end();
  }
}

/*
 * Copyright 2018 inpwtepydjuf@gmail.com
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

class PacketLoggerApplication extends BaseApplication {
  applicationTitle = "C:\\Users\\inpwt\\Downloads\\windump.exe"

  def launch(): Unit = {
    application = automation.launchWithDirectory(applicationTitle,
                                "-p",
                                "-nn",
                                "-N",
                                "-wcapture.dump",
                                "-X",
                                "-s 0",
                                "-i 1",
                                "host 192.168.1.254")

    // A short wait for the call to gather some information
    Thread.sleep(10000)
  }

  def dump(): Unit = {
    Thread.sleep(1000); // So it can write the file properly
    automation.launchWithRedirect(applicationTitle,
      "-rC:\\Users\\inpwt\\Downloads\\capture.dump",
      "-nnvvvSeXX")
  }

  override def exit(): Unit = {
    application.end()
  }
}
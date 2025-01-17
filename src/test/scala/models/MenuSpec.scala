/*
 * Copyright 2023 Luke A Jones
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

package models

import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec

import scala.language.postfixOps

class MenuSpec extends AnyWordSpec with should.Matchers {

  val testMenu: Menu = Menu(Map(
    "Cola" -> 0.5,
    "Coffee" -> 1.0,
    "Cheese Sandwich" -> 2.0,
    "Steak Sandwich" -> 4.5
  ))

  "Menu.generateTotalBill" when {
    "given an empty list of items" should {
      "return the total bill as zero" in {
        Menu.generateTotalBill(testMenu, Nil) shouldBe 0
      }
    }
  }
}

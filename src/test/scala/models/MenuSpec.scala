/*
 * Copyright 2025 Luke A Jones
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

  val testMenu: Menu = Menu(Seq(Cola, Coffee, CheeseSandwich, SteakSandwich))

  "Menu.generateTotalBill" when {
    "given an empty list of items" should {
      "return the total bill as zero" in {
        Menu.generateTotalBill(testMenu, Nil) shouldBe 0
      }
    }

    "given a list containing one valid item" should {
      "return the appropriate total bill applying any applicable service charges" in {
        Menu.generateTotalBill(testMenu, Seq("Cola")) shouldBe 0.5
      }
    }

    "given a list containing only drink items" should {
      "return the appropriate total bill without a service charge applied" in {
        Menu.generateTotalBill(testMenu, Seq("Cola", "Coffee")) shouldBe 1.5
      }
    }

    "given a list containing cold food items, but not hot food items" should {
      "return the appropriate total bill with a 10% service charge applied" in {
        Menu.generateTotalBill(testMenu, Seq("Cola", "Coffee", "Cheese Sandwich")) shouldBe 3.85
      }
    }

    "given a list containing hot food items" should {
      "apply a 20% service charge to the bill when the total service charge does not exceed £20" in {
        Menu.generateTotalBill(testMenu, Seq("Cola", "Coffee", "Steak Sandwich")) shouldBe 7.2
      }

      "cap the service charge to £20 when appropriate" in {
        Menu.generateTotalBill(testMenu, Seq.fill(100)("Steak Sandwich")) shouldBe 470.0
      }
    }

    "given a list containing an invalid item" should {
      "throw an exception" in {
        assertThrows[NoSuchElementException](Menu.generateTotalBill(testMenu, Seq("foobar")))
      }
    }
  }
}

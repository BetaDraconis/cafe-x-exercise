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

sealed trait MenuItem {
  val name: String
  val price: BigDecimal
  val isHot: Boolean
  val isFood: Boolean
}

case class Drink(name: String, price: BigDecimal, isHot: Boolean) extends MenuItem {
  override val isFood: Boolean = false
}

object Coffee extends Drink("Coffee", 1, true)
object Cola extends Drink("Cola", 0.5, false)

case class Food(name: String, price: BigDecimal, isHot: Boolean) extends MenuItem {
  override val isFood: Boolean = true
}

object CheeseSandwich extends Food("Cheese Sandwich", 2.0, false)
object SteakSandwich extends Food("Steak Sandwich", 4.5, true)

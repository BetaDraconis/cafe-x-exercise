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

case class MenuItem(name: String, price: BigDecimal, isHot: Boolean, isFood: Boolean)

object Coffee extends MenuItem(name = "Coffee", price = 1.0, isHot = true, isFood = false)
object Cola extends MenuItem(name = "Cola", price = 0.5, isHot = false, isFood = false)
object CheeseSandwich extends MenuItem(name = "Cheese Sandwich", price = 2.0, isHot = false, isFood = true)
object SteakSandwich extends MenuItem(name = "Steak Sandwich", price = 4.5, isHot = true, isFood = true)

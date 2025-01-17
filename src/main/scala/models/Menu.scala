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

case class Menu(items: Seq[MenuItem]) {
  val itemMap: Map[String, MenuItem] = items.map(elem => elem.name -> elem).toMap
}

object Menu {
  def generateTotalBill(menu: Menu, billItems: Seq[String]): BigDecimal =
    val (baseSum, orderHasFood, orderHasHotFood) = billItems.foldLeft(BigDecimal(0.0), false, false)((state, nextElem) =>
      val (sum, orderHasFood, orderHasHotFood) = state
      
      // This is unsafe as an exception will be thrown if an item is not found in the menu
      // I've chosen not to address this as the task called for the simplest possible solution
      val menuItem: MenuItem = menu.itemMap(nextElem)
      
      val newSum: BigDecimal = sum + menuItem.price
      val newHasFood: Boolean = orderHasFood || menuItem.isFood
      val newHasHotFood: Boolean = orderHasHotFood || (menuItem.isHot && menuItem.isFood)
      (newSum, newHasFood, newHasHotFood)
    )

    (orderHasFood, orderHasHotFood) match {
      case (false, _) => baseSum
      case (_, false) => applyServiceCharge(baseSum, 0.1, None)
      case _ => applyServiceCharge(baseSum, 0.2, Some(20))
    }

  private def applyServiceCharge(sum: BigDecimal, chargeRatio: BigDecimal, capOpt: Option[BigDecimal]): BigDecimal = {
    val baseCharge = sum * chargeRatio
    val cappedCharge = capOpt.fold(baseCharge)(cap => if (baseCharge > cap) cap else baseCharge)
    
    val roundedNewSum = (sum + cappedCharge).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    roundedNewSum
  }
}

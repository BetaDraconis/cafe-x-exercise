package models

case class Menu(items: Map[String, MenuItem])

object Menu {
  def generateTotalBill(menu: Menu, billItems: Seq[String]): BigDecimal =
    val (baseSum, orderHasFood, orderHasHotFood) = billItems.foldLeft(BigDecimal(0.0), false, false)((state, nextElem) =>
      val (sum, orderHasFood, orderHasHotFood) = state
      
      // This is unsafe as if any items on the bill are incorrect we will throw an exception here
      val item = menu.items(nextElem)
      val newSum = sum + item.price
      val newHasFood = orderHasFood || item.isFood
      val newHasHotFood = orderHasHotFood || (item.isHot && item.isFood)
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

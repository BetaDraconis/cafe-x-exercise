package models

case class Menu(items: Map[String, Double])

object Menu {
  def generateTotalBill(menu: Menu, billItems: Seq[String]): Double =
    billItems.foldLeft(0d)((sum, nextElem) => sum + menu.items(nextElem))
}

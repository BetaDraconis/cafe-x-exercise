package models

case class Menu(items: Map[String, Double])

object Menu {
  def generateTotalBill(menu: Menu, billItems: Seq[String]): Double = ???
}

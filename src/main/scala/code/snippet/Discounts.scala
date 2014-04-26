package code.snippet

import net.liftweb.util._
import Helpers._
import net.liftweb.http.SHtml
import scala.collection.mutable.ListBuffer
import net.liftweb.http.js.JsCmds

class Discounts {
  case class Product(name: String, price: Int)
  case class Option(name: String)

  trait SomeDiscount {
    var discount: Int = 50
    var remark: String = ""
  }
  trait Updatable[T] {
    def update(value: T)
  }

  class ProductDiscount extends SomeDiscount with Updatable[Product] {
    var product: Product = null
    def update(value: Product) { product = value }
  }

  class OptionDiscount extends SomeDiscount with Updatable[Option] {
    var option: Option = null
    override def update(value: Option) { option = value }
  }

  val products = List(Product("first product", 12), Product("second product", 23), Product("third product", 11))
  val options = List(Option("1st option"), Option("2nd option"))
  val productDiscounts = ListBuffer[ProductDiscount]()
  val optionDiscounts = ListBuffer[OptionDiscount]()

  def renderProducts = render(productDiscounts, products)(_.product)(() => new ProductDiscount)
  def renderOptions = render(optionDiscounts, options)(_.option)(() => new OptionDiscount)

  def render[T <: SomeDiscount with Updatable[Y], Y <: { def name: String } ](someDiscounts: ListBuffer[T], allItems: List[Y])(getItem: T => Y)(newItem: () => T) =
    SHtml.idMemoize(outer =>
      ".productDiscount *" #> someDiscounts.map { line =>
        ".product" #> SHtml.ajaxSelectObj[Y](allItems.map(p => (p, p.name)), Some(getItem(line)), (s: Y) => { line.update(s); JsCmds.Noop }) &
          ".discount" #> SHtml.ajaxText(line.discount.toString, { s => line.discount = Integer.parseInt(s); JsCmds.Noop }) &
          ".delete [onclick]" #> SHtml.ajaxInvoke(() => { someDiscounts -= line; outer.setHtml()})
      } &
        "#newProductDiscount [onclick]" #> {
          SHtml.ajaxInvoke(() => {
            someDiscounts += newItem()
            outer.setHtml()
          })
        }
    )
}

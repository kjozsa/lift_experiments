package code.snippet

import net.liftweb._
import http.js.JsCmds
import http.SHtml
import util._
import Helpers._
import net.liftweb.http.SHtml._
import code.model.Company
import code.model.Database
import org.squeryl.PrimitiveTypeMode._


object DB1 {

  def render =
    ".createRecord [onclick]" #> ajaxInvoke(() => {
      val ftl = Company.createRecord.name("FTL Development " + randomInt(100))
      Database.companies.insert(ftl)

      JsCmds.Noop
    }) &
      "li" #> Database.companies.map(company => {
        "ul" #> company.name.toString
      })

}

//
//
//object ShowMemoize {
//  def render =
//    "div" #> SHtml.idMemoize(
//      outer =>
//      // redraw the whole div when this button is pressed
//        "@refresh_all [onclick]" #> SHtml.ajaxInvoke(outer.setHtml _) &
//
//          // deal with the "one" div
//          "@one" #> SHtml.idMemoize(
//            one =>
//              "span *+" #> now.toString & // display the time
//                "button [onclick]" #> SHtml.ajaxInvoke(one.setHtml _)) & // redraw
//
//          // deal with the "two" div
//          "@two" #> SHtml.idMemoize(
//            two => // the "two" div
//            // display a bunch of items
//              "ul *" #> (0 to randomInt(6)).map(i => "li *+" #> i) &
//                // update the "two" div on button press
//                "button [onclick]" #> SHtml.ajaxInvoke(two.setHtml _)))
//}

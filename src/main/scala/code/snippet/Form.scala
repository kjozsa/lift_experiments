package code.snippet

import java.util.Date
import _root_.net.liftweb._
import http._

import common._
import util._
import Helpers._

object Form {

  object name extends RequestVar[String]("")

  object date extends RequestVar[Box[Date]](Full(new Date))

  def render =
    "name=name" #> SHtml.text(name.is, name(_)) &
      "name=date" #> SHtml.text(date.map(_.toString).openOr(""), input => date(tryo(new Date(Date.parse(input)))))
}

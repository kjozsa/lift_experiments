package code.snippet

import net.liftweb.util._
import Helpers._
import net.liftweb.http.SHtml
import scala.collection.mutable.ListBuffer
import net.liftweb.http.js.JsCmds

class PasswordChange {

  case class PasswordData(original: String, password: String, password2: String)

  val data = new PasswordData("original", null, null)

  def render =
    "original" #> SHtml.password(data.original, z => null)
}

package code.snippet

import net.liftweb.http.LiftScreen
import net.liftweb.http.S

object ScreenForm extends LiftScreen {

  val name = field("Name", "")
  val age: Int = field("Age", 0, minVal(13, "Too young"))

  def finish() {
    S.notice("Form submitted")
  }
}
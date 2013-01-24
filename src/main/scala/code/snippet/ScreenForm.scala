package code.snippet

import net.liftweb.http.LiftScreen
import net.liftweb.http.S

object ScreenForm extends LiftScreen {
  val name = field("Name", "")
  val age: Int = field("Age", 0, minVal(13, "Too young"))

  abstract sealed class Gender

  case object Male extends Gender

  case object Female extends Gender

  val gender = select("Gender", Male, Seq(Male, Female))

  def finish() {
    S.notice("Form submitted, chosen gender: " + gender)
  }
}

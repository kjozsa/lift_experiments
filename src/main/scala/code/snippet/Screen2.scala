package code.snippet

import net.liftweb.http._
import js.JsCmds.SetHtml
import xml.NodeSeq
import net.liftweb.http.FieldBinding.Self
import net.liftweb.util.FieldError

trait ExpLiftScreen extends CssBoundLiftScreen {
  override def allTemplate = savedDefaultXml

  override def defaultFieldNodeSeq: NodeSeq =
    <div>
      <label class="label field"></label>
      <span class="value fieldValue"></span>
      <span class="help"></span>
      <div class="errors">
        <div class="error"></div>
      </div>
    </div>
}


object Screen2 extends ExpLiftScreen {
  val firstName = field("First Name", "", FieldBinding("firstName"))
  val lastName = field("Last Name", "", FieldBinding("lastName"))
  val city = field("City", "", FieldBinding("city"))
  val address = field("Adress", "", FieldBinding("address"))
  val zip = field("Zip", "", FieldBinding("zip"))

  def firstNameValid: Errors = if (firstName.is.isEmpty) List(FieldError(firstName, "first name empty")) else Nil

  override def validations = firstNameValid _ :: super.validations



  def formName = "person"

  def finish() {
    println("screen submitted: " + firstName.is.isEmpty)
    AjaxOnDone.set(SetHtml("fields", <b>
      All done!
    </b>))
  }
}

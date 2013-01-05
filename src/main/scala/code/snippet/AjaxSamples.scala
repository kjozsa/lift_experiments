package code.snippet

import net.liftweb.http.js.JsCmds

import net.liftweb.http.SHtml
import net.liftweb.util.Helpers.strToCssBindPromoter

class AjaxSamples {

  def render = {
    "#sampleButton" #> SHtml.ajaxButton("action button", () => {
      println("ajax button clicked")
      JsCmds.Noop
    }) &
    "#sampleLink" #>
      SHtml.a(() => {
        println("ajax link clicked")
        JsCmds.Noop
      }, <span>action link</span>)
}

}

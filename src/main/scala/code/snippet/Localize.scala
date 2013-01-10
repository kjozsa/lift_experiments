package code.snippet

import net.liftweb.http.S
import net.liftweb.util.Helpers.strToCssBindPromoter

class Localize {

  def render =
    "#span1" #> S.?("hello")

}

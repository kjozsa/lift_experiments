package code.snippet

import scala.math.random
import net.liftweb.common.Full
import net.liftweb.http.S
import net.liftweb.http.SHtml
import net.liftweb.http.StatefulSnippet
import net.liftweb.util.Helpers.asInt
import net.liftweb.util.Helpers.strToCssBindPromoter

class Game extends StatefulSnippet {
  private val origin = S.originalRequest.map(_.request.uri) openOr (sys.error("no request"))
  private val secret = (1 + random * 100).toInt
  private var guess = ""
  private var count = 0

  def dispatch = { case "render" => render }

  def render =
    "name=guess" #> SHtml.text(guess, { count += 1; guess = _ }) &
      "type=submit" #> SHtml.onSubmitUnit(submit)


  def submit() = {
    println("matching " + guess + " on secret " + secret)

    asInt(guess) match {
      case Full(a) if a < secret => S.notice("try higher")
      case Full(a) if a > secret => S.notice("try lower")
      case Full(a) if a == secret => {
        S.notice("YOU WON in " + count + " guesses!")
        S.redirectTo(origin)
      }
      case _ =>
    }

    this.redirectTo(origin)
  }
}
package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._
import common._
import http._
import sitemap._
import Loc._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._
import mapper._
import net.liftweb.squerylrecord.SquerylRecord
import org.squeryl.adapters.H2Adapter
import org.squeryl.Session
import java.sql.DriverManager
import net.liftweb.common.Logger
import code.model.Database
import org.squeryl.PrimitiveTypeMode._

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Logger {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    LiftRules.setSiteMapFunc(MenuInfo.sitemap)

    setupDB
  }

  def setupDB {
    Class.forName("org.h2.Driver");
    SquerylRecord.initWithSquerylSession(Session.create(
      DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", ""), new H2Adapter))

    transaction {
      Database.drop
      Database.printDdl
      Database.create
    }
  }

  object MenuInfo {
    import Loc._

    def sitemap() = SiteMap(
      Menu("welcome") / "index",
      Menu("number guess!") / "numberguess",
      Menu("screen example") / "screen")
  }
}

import org.eclipse.jetty.server.Server

import org.eclipse.jetty.servlet.{ DefaultServlet, ServletContextHandler }
import net.liftweb.http.LiftFilter
import java.util.EnumSet
import javax.servlet.DispatcherType

object JettyLauncher extends App {
  val port = if (System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080
  val server = new Server(port)

  val context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS)
  context.addServlet(classOf[DefaultServlet], "/")
  context.addFilter(classOf[LiftFilter], "/*", EnumSet.of(DispatcherType.REQUEST))
  context.setResourceBase("src/main/webapp")

  server.start
  server.join
}
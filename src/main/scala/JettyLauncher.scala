import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ DefaultServlet, ServletContextHandler }
import org.eclipse.jetty.server.nio.SelectChannelConnector
import net.liftweb.http.LiftFilter
import java.util.EnumSet
import javax.servlet.DispatcherType
import org.eclipse.jetty.webapp.WebAppContext

object JettyLauncher extends App {
  val port = if (System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080
  val server = new Server
  val scc = new SelectChannelConnector
  scc.setPort(port)
  server.setConnectors(Array(scc))

  val context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS)
  context.addServlet(classOf[DefaultServlet], "/");
  context.addFilter(classOf[LiftFilter], "/*", EnumSet.of(DispatcherType.REQUEST))
  context.setResourceBase("src/main/webapp")

  server.start
  server.join
}
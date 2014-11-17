name := "Lift Experiments"

version := "0.0.1"

organization := "net.liftweb"

scalaVersion := "2.10.4"

resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
                "releases"        at "http://oss.sonatype.org/content/repositories/releases"
                )

jetty()

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= {
  val liftVersion = "2.6-M3"
  Seq(
    "net.liftweb"       %% "lift-webkit"        % liftVersion        % "compile",
    "net.liftweb"       %% "lift-mapper"        % liftVersion        % "compile",
    "net.liftweb"       %% "lift-squeryl-record"        % liftVersion        % "compile",
    "org.eclipse.jetty" % "jetty-webapp"        % "9.2.0.M0"  % "container,test,compile",
    "javax.servlet"     % "javax.servlet-api"   % "3.0.1"     % "provided",
    "ch.qos.logback"    % "logback-classic"     % "1.1.2",
    "com.h2database" % "h2" % "1.3.176"
  )
}



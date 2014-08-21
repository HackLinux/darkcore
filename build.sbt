name := "Darkcore"

version := "α.0.0"

scalaVersion := "2.10.3"

resolvers ++= Seq(
"scct-github-repository" at "http://mtkopone.github.com/scct/maven-repo"
)

libraryDependencies += "edu.berkeley.cs" %% "chisel" % "2.2.1"

mainClass in Compile := Some("main.main")

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "task-1-19"
  )

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC7"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test


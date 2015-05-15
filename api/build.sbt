name := """app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
	javaJdbc,
	javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0"),
	"org.hibernate" % "hibernate-entitymanager" % "4.2.8.Final",
	"mysql" % "mysql-connector-java" % "5.1.18"
)
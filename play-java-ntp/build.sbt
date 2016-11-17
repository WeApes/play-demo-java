name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava).enablePlugins(PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,
  evolutions,
  "org.mindrot" % "jbcrypt" % "0.3m", // BCrypt是一个常用的密码存储算法
  "mysql" % "mysql-connector-java" % "5.1.18",
  "com.github.axet" % "kaptcha" % "0.0.9", // Kaptcha是一个验证码生成器
  "com.typesafe.play" %% "play-mailer" % "5.0.0"
)


fork in run := true
import sbt._
import sbt.Keys._

object ProjectBuild extends Build {
  import Settings._

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = parentSettings,
    aggregate = Seq(flowerCore, flowerLogback)
  )

  lazy val flowerCore = Project(
    id = "flower-core",
    base = file("./modules/flower-core"),
    settings = defaultSettings ++ Seq(libraryDependencies ++= Dependencies.flowerCore)
  )

  lazy val flowerLogback = Project(
    id = "flower-logback",
    base = file("./modules/flower-logback"),
    settings = defaultSettings ++ Seq(libraryDependencies ++= Dependencies.flowerLogback)
  )

  lazy val flowerDemo = Project(
    id = "flower-demo",
    base = file("./modules/flower-demo"),
    settings = defaultSettings ++ Seq(libraryDependencies ++= Dependencies.flowerDemo)
  )
}

object Dependencies {
  import Versions._

  object Compile {
    val config        = "com.typesafe"             % "config"           % TypesafeConfigVer
    val logback       = "ch.qos.logback"           % "logback-classic"  % LogbackVer
  }

  object Test {
    val scalatest     = "org.scalatest"           %% "scalatest"            % ScalaTestVer      % "test"
    val scalacheck    = "org.scalacheck"          %% "scalacheck"           % ScalaCheckVer     % "test"
    val junit         = "junit"                    % "junit"                % JunitVer          % "test"

    val abideCore     = "com.typesafe"             % "abide-core_2.11"      % AbideCoreVer      % "abide,test"
    val abideExtra    = "com.typesafe"             % "abide-extra_2.11"     % AbideExtraVer     % "abide,test"
  }

  import Compile._
  import Test._

  val test = Seq(Test.scalatest, Test.scalacheck, Test.junit)

  /** Module deps */

  val flowerCore = Seq(config) ++ test
  val flowerLogback = Seq(logback, junit)
  val flowerDemo = Seq(config) ++ test
}

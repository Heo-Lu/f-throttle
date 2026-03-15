enablePlugins(JavaAppPackaging)
enablePlugins(PekkoGrpcPlugin)

scriptClasspath := Seq("*")

PB.protocVersion := "3.25.5"

val pekkoVersion = "1.1.5"
val nettyVersion = "4.2.0.Final"

ThisBuild / version := "0.0.0"
ThisBuild / scalaVersion := "3.3.5"

val scala: String = (ThisBuild / scalaVersion).key.label

mainClass := Some("io.fth.App")

excludeDependencies += ExclusionRule("org.apache.pekko", "pekko-grpc-runtime_3")

lazy val root = project
  .in(file("."))
  .settings(
    name := "testPrj",
    Compile / scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked"),
    Compile / javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),
    run / javaOptions ++= Seq("-Xms128m", "-Xmx1024m", "-Djava.library.path=./target/native"),
    libraryDependencies ++= Seq(
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion exclude("org.slf4j", "slf4j-api"),
      "org.apache.pekko" %% "pekko-cluster-typed" % pekkoVersion exclude("org.slf4j", "slf4j-api"),
      "org.apache.pekko" %% "pekko-stream" % pekkoVersion exclude("org.slf4j", "slf4j-api"),
      "org.apache.pekko" %% "pekko-serialization-jackson" % pekkoVersion exclude("org.slf4j", "slf4j-api"),
      "org.apache.pekko" %% "pekko-discovery" % pekkoVersion exclude("org.slf4j", "slf4j-api"),
      "org.apache.pekko" %% "pekko-pki" % pekkoVersion exclude("org.slf4j", "slf4j-api"),

      "org.apache.pekko" %% "pekko-http" % "1.2.0" exclude("org.slf4j", "slf4j-api"),
      "org.apache.pekko" %% "pekko-http-cors" % "1.2.0" exclude("org.slf4j", "slf4j-api"),

      "io.grpc" % "grpc-netty" % "1.74.0",
      "io.grpc" % "grpc-netty-shaded" % "1.74.0",
      "io.grpc" % "grpc-protobuf" % "1.74.0",

      "com.typesafe" % "config" % "1.4.3",
      "org.scala-lang.modules" %% "scala-parser-combinators" % "2.4.0",


      "ch.qos.logback" % "logback-classic" % "1.5.6",
      "io.netty" % "netty-codec-http2" % nettyVersion,
      "io.netty" % "netty-pkitesting" % nettyVersion,
      "io.netty" % "netty-all" % nettyVersion,
      "io.netty" % "netty-transport" % nettyVersion,
      "org.bouncycastle" % "bcprov-jdk15on" % "1.70" % "runtime",
      "org.bouncycastle" % "bcpkix-jdk15on" % "1.70" % "runtime",
      ///////// gatling ///////////////
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
      "org.jodd" % "jodd-lagarto" % "6.0.6", // css
      "io.suzaku" %% "boopickle" % "1.5.0", // stats
      "com.github.ben-manes.caffeine" % "caffeine" % "3.2.0", // Cache
      "io.burt" % "jmespath-core" % "0.5.0", // CheckSupport
      "io.burt" % "jmespath-jackson" % "0.5.0", // CheckSupport
      "net.sf.saxon" % "Saxon-HE" % "10.5", // CheckSupport
      "com.softwaremill.quicklens" %% "quicklens" % "1.7.4", // SourceFeederBuilder
      "org.simpleflatmapper" % "lightning-csv" % "8.2.3", // SeparatedValuesParser
      "com.tdunning" % "t-digest" % "3.3" % "runtime", 
      ////////////////////////////////////////////
      "org.reactivestreams" % "reactive-streams" % "1.0.4" exclude("org.slf4j", "slf4j-api"),
      "org.zeroturnaround" % "zt-exec" % "1.9",
      "com.github.scopt" %% "scopt" % "4.1.0",
      "org.influxdb" % "influxdb-java" % "2.23",
      "de.jensd" % "fontawesomefx" % "8.9",
      "org.reflections" % "reflections" % "0.10.2", //exclude("org.slf4j", "slf4j-api"),
      "com.github.librepdf" % "openpdf" % "1.3.30",
      "com.lowagie" % "itext" % "4.2.2" pomOnly(), // "com.lowagie" % "itext" % "2.1.7",
      "org.scala-lang.modules" %% "scala-xml" % "2.1.0",
      "org.xmlunit" % "xmlunit-core" % "2.10.0", 
      "com.google.auto.service" % "auto-service" % "1.1.1",
      "org.postgresql" % "postgresql" % "42.2.27",
      "org.apache.kafka" % "kafka-clients" % "3.4.0",
      "javax.jms" % "javax.jms-api" % "2.0",
      "jakarta.jms" % "jakarta.jms-api" % "3.1.0",
      "com.ibm.mq" % "com.ibm.mq.allclient" % "9.2.2.0",
      "com.github.vertical-blank" % "sql-formatter" % "2.0.5",
      "com.decodified" %% "scala-ssh" % "0.11.1",
      "org.apache.activemq" % "artemis-jms-client-all" % "2.19.0" % "runtime",
      "junit" % "junit" % "4.12" % Test,
      "org.scalatest" %% "scalatest" % "3.2.18" % Test),
    run / fork := true,
    Global / cancelable := false,
    // disable parallel tests
    Test / parallelExecution := false
  )
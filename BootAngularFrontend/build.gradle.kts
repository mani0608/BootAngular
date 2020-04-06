import com.moowork.gradle.node.NodeExtension
import com.moowork.gradle.node.npm.NpmTask

plugins {
  java
}

apply {
  plugin("com.moowork.node")
}

tasks {
  register<NpmTask>("npmStart") {
    setArgs(listOf("start"))
  }
  register<NpmTask>("npmProdBuild") {
    setArgs(listOf("run", "prod"))
    dependsOn ("npm_install")
  }
}

configure<NodeExtension> {
  version = "13.12.0"
  npmVersion = "6.14.4"
  download = false
}

tasks.getByName<Jar>("jar") {
  dependsOn ("npm_run_build")
  from ("dist/BootAngularFrontend")
  into ("static")
}

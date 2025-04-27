import com.github.jengelman.gradle.plugins.shadow.transformers.ApacheLicenseResourceTransformer
import com.github.jengelman.gradle.plugins.shadow.transformers.ApacheNoticeResourceTransformer
import com.github.jengelman.gradle.plugins.shadow.transformers.ServiceFileTransformer

group = "fr.xephi"
version = "2.2.0-SNAPSHOT"
description = "BungeeCord addon for AuthMe!"
// Inception year: 2017
// Url: https://www.spigotmc.org/resources/authmebungee.50219/
// Organization: AuthMe-Team (https://github.com/AuthMe)
// CI Management: Jenkins (http://ci.codemc.org/job/AuthMeBungee/)
// Issue Management: GitHub (https://github.com/AuthMe/AuthMeBungee/issues)
// License: GPLv3 (http://www.gnu.org/licenses/gpl-3.0.html)

plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.6"
}

java {
    //sourceCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
    withJavadocJar()
}

tasks.processResources {
    from(projectDir) {
        include("LICENSE")
    }

    expand(mapOf("version" to rootProject.version))
}

tasks.shadowJar {
    archiveFileName = "AuthMeBungee-$version.jar"
    // We want the shaded jar to be the project's default artifact
    archiveClassifier = ""

    // Remove all classes of dependencies that are not used by the project
    minimize()

    // Relocations
    val libsPath = "${rootProject.group}.${rootProject.name}.libs."
    relocate("javax.annotation", libsPath + "javax.annotation")
    relocate("javax.inject", libsPath + "javax.inject")
    relocate("ch.jalu.injector", libsPath + "jalu.injector")
    relocate("ch.jalu.configme", libsPath + "jalu.configme")
    relocate("org.yaml.snakeyaml", libsPath + "yaml.snakeyaml")
    relocate("org.bstats", libsPath + "org.bstats")

    // Transformers
    transform(ApacheLicenseResourceTransformer())
    transform(ServiceFileTransformer())

    val apacheNoticeResourceTransformer = ApacheNoticeResourceTransformer()
    apacheNoticeResourceTransformer.addHeader = false
    transform(apacheNoticeResourceTransformer)

    // Files exclusion
    // Some jar index or cryptographic signature files are already excluded by default by the Shadow Plugin
    exclude("META-INF/**")
}

publishing {
    publications.create<MavenPublication>("shadow") {
        from(components["shadow"])
    }
}

repositories {
    mavenCentral()

    maven {
        // PaperMC Repository
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

// Dependencies versions
val injectorVersion     = "1.0"
val configMeVersion     = "1.4.1"
val bStatsVersion       = "3.0.2"
val bungeeCordVersion   = "1.19-R0.1-SNAPSHOT"

dependencies {
    // Dependency injection
    implementation("ch.jalu", "injector", injectorVersion)

    // Configuration library
    implementation("ch.jalu", "configme", configMeVersion)

    // Metrics
    implementation("org.bstats", "bstats-bungeecord", bStatsVersion)

    // BungeeCord API
    compileOnly("net.md-5", "bungeecord-api", bungeeCordVersion)
}

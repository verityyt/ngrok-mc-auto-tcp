plugins {
    java
    kotlin("jvm") version "1.4.21"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.jar {
    archiveName = "NgrokMcAutoTcp.jar"

    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)

    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    manifest {
        attributes["Main-Class"] = "NgrokMcAutoTcp"
    }

    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
}
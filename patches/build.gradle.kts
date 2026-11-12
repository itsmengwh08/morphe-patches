group = "itsmengwh08.zaloxposed.4morphe"

patches {
    // TODO: Update this section with your project details.
    about {
        name = "Hiew's ZaloMorphe fork"
        description = "Make ZaloMorphe better, up-to-date"
        source = "git@github.com:itsmengwh08/morphe-patches"
        author = "itsmengwh08"
        contact = "https://m.me/itsmengwh08"
        website = "https://facebook.com/itsmengwh08"
        license = "GPLv3"
    }
}

// Separate configuration so gson is available at runtime for the
// generatePatchesList task but never bundled into the APK.
val patchListGeneratorClasspath = configurations.create("patchListGeneratorClasspath")

dependencies {
    compileOnly(libs.gson)
    patchListGeneratorClasspath(libs.gson)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build)

        classpath = sourceSets["main"].runtimeClasspath + patchListGeneratorClasspath
        mainClass.set("util.PatchListGeneratorKt")
    }

    // Used by gradle-semantic-release-plugin.
    publish {
        dependsOn("generatePatchesList")
    }
}

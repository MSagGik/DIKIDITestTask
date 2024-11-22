plugins {
    `kotlin-dsl`
    id("java-gradle-plugin")
}

group = "com.msaggik.dikiditesttask.plugins"

gradlePlugin {
    plugins {
        create("developPropertiesPlugin") {
            id = "com.msaggik.dikiditesttask.plugins.developproperties"
            implementationClass = "com.msaggik.dikiditesttask.plugins.developproperties.DevelopPropertiesPlugin"
        }
    }
}

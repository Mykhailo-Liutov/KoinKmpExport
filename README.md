# KoinKmpExport

This simple library solves a problem of needing to manually expose Koin dependencies to be accessible from iOS when using KMP. 

## Setup

Configure your KMP module by:

1. Adding dependency on `core` module to be able to add the annotation.

   ```
      sourceSets {
        commonMain {
            dependencies {
                implementation("TODO")
            }
        }
    }
   ```
2. Adding dependency on the processor and configuring it:

   ```
      dependencies {
         add("kspCommonMainMetadata", TODO)
       }

    tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
        if (name.contains("ios", ignoreCase = true)) {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }

    kotlin.sourceSets.commonMain {
      kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }
   ```

## Usage

To mark a class than needs to be exported, annotate it with @KoinKmmExport. That's it, export will be automatically generated when building iOS target.

## Example

Annotated class:

```
@KoinKmmExport
class SampleUseCase {

    operator fun invoke(): String = "Hello!"

}
```

Generated export:

```
  public val Koin.sampleUseCase: SampleUseCase
    get() = get()
```

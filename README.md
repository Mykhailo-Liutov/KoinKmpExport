# KoinKmpExport

This simple library solves a problem of needing to manually expose Koin dependencies to be accessible from iOS when using KMP. 

## Setup

Configure your KMP module by:

1. Adding dependency on `core` module to be able to add the annotation.

   ```
      sourceSets {
        commonMain {
            dependencies {
                implementation("io.github.mykhailo-liutov:koinexport-core:1.1")
            }
        }
    }
   ```
2. Adding dependency on the ksp processor and configuring it:

   ```
    dependencies {
        add("kspIosSimulatorArm64", "io.github.mykhailo-liutov:koinexport-processor:1.1")
        add("kspIosX64", "io.github.mykhailo-liutov:koinexport-processor:1.1")
        add("kspIosArm64", "io.github.mykhailo-liutov:koinexport-processor:1.1")
    }

    kotlin.sourceSets.commonMain {
      kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }
   ```

## Usage

There are some arguments that have to be provided to configure the exports. Example configuration might look like this:

```
ksp {
    arg("packageName", "io.github.mykhailoliutov.koinexport.sample")
    arg("exportsFileName", "AppDependency")
    arg("mode", "properties")
}

```

- "packageName" - package, in which the file with exports will be generated.
- "exportsFileName" - the name of the file which will contain the exports
- "mode" - generation mode of the processor, can be either "extensions" or "properties", depending on which one is more convenient for you.

When using mode "extensions", exports will be generate as top-level extensions on Koin. For example:

```
public val Koin.sampleUseCase: SampleUseCase
get() = get()
```

When using mode "properties", exports will be generated as properties within a generated class, with same name as the file name. For example:

```
public class AppDependency : KoinComponent {
  public val sampleUseCase: SampleUseCase
    get() = get()
}
```

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

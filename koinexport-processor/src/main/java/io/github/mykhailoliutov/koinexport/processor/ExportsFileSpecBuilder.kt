package io.github.mykhailoliutov.koinexport.processor

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.Import
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.koin.core.Koin
import org.koin.core.component.KoinComponent


internal class FileSpecBuilder(
    packageName: String,
    private val exportsFileName: String,
    mode: GenerationMode
) : KoinComponent {

    private val fileSpecBuilder = FileSpec.builder(packageName, exportsFileName)
    private val classSpecBuilder = when (mode) {
        GenerationMode.Properties -> createPropertiesWrapperBuilder()
        GenerationMode.Extensions -> null
    }

    private fun createPropertiesWrapperBuilder(): TypeSpec.Builder =
        TypeSpec.classBuilder(exportsFileName)
            .addSuperinterface(KoinComponent::class)

    fun addProperty(propertySpec: PropertySpec.Builder) {
        if (classSpecBuilder == null) {
            propertySpec.receiver(Koin::class)
            fileSpecBuilder.addProperty(propertySpec.build())
        } else {
            classSpecBuilder.addProperty(propertySpec.build())
        }
    }

    fun build(): FileSpec {
        if (classSpecBuilder != null) {
            fileSpecBuilder.addImport("org.koin.core.component", "get")
            fileSpecBuilder.addType(classSpecBuilder.build())
        }
        return fileSpecBuilder.build()
    }

}
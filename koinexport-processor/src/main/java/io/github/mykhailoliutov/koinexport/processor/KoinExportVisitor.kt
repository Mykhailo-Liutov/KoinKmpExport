package io.github.mykhailoliutov.koinexport.processor

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import org.koin.core.Koin

internal class KoinExportVisitor(
    private val addExportProperty: (PropertySpec.Builder) -> Unit
) : KSVisitorVoid() {

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        val className = classDeclaration.simpleName.asString()
        val packageName = classDeclaration.packageName.asString()

        val extensionPropertyName = className.replaceFirstChar { it.lowercase() }

        val getterSpec = FunSpec.getterBuilder()
            .addStatement("return get()")
            .build()

        val propertySpec =
            PropertySpec.builder(extensionPropertyName, ClassName(packageName, className))
                .getter(getterSpec)

        addExportProperty(propertySpec)
    }
}
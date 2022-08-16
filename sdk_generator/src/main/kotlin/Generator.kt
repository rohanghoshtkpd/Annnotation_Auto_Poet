import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement


@AutoService(Processor::class)
class Generator : AbstractProcessor() {

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(MyConstant::class.java.name)
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
       val elementWithMyConstants = roundEnv?.getElementsAnnotatedWith(MyConstant::class.java)



        generateAKotlinFile()

        return true
    }

    private fun generateAKotlinFile() {
        val packageName = "com.demo.rohan"
        val fileName = "RohanDemoGenerated"
        val objBuilder = TypeSpec.objectBuilder(fileName)

        val file = FileSpec.builder(packageName,fileName).addType(objBuilder.build()).build()
        val generateDir = processingEnv.options[KAPT_KOTLIN_GENRETAED_OPTION_NAME]
        file.writeTo(File(generateDir,"$fileName.kt"))



    }

    companion object{
        const val KAPT_KOTLIN_GENRETAED_OPTION_NAME = "kapt.kotlin.generated"
    }
}


@Target(AnnotationTarget.CLASS,AnnotationTarget.FIELD,AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class MyConstant  (
    val proName:String,
    val propValue:String)
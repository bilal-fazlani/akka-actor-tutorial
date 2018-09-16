package VI_hotswapping_behavior.messages

abstract sealed class Greeting(val text: String)

final case class Hello(override val text:String = "hello") extends Greeting(text)
final case class Namaste(override val text:String = "नमस्ते") extends Greeting(text)
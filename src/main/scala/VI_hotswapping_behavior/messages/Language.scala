package VI_hotswapping_behavior.messages

abstract sealed class Language

final case class Hindi() extends Language
final case class English() extends Language

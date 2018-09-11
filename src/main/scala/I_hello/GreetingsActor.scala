package I_hello
import akka.actor.{Actor, Props}

class GreetingsActor extends Actor {
  override def receive: Receive = {
    case Hello(times) => println("Hello\n" * times)
    case _ => unhandled()
  }
}

object GreetingsActor{
  def props = Props(classOf[GreetingsActor])
}

case class Hello(times: Int = 1)
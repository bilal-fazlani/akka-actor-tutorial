package IV_forward_example

import IV_forward_example.messages.{Hello, HelloResponse}
import akka.actor.{Actor, Props}

class GreetingsActor extends Actor {

  override def receive: Receive = {
    case Hello(times) =>
      sender ! HelloResponse("hello\n" * times)
    case _ => unhandled()
  }
}

object GreetingsActor{
  def props = Props(classOf[GreetingsActor])
}
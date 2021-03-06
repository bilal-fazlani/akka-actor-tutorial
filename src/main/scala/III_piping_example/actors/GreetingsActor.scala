package III_piping_example.actors

import III_piping_example.messages._
import akka.actor.{Actor, Props}

class GreetingsActor extends Actor {

  var count = 0

  override def receive: Receive = {
    case Hello(times) =>
      count +=1
      sender ! HelloResponse("hello\n" * times)
    case Count() => sender ! CountResponse(count)
    case _ => unhandled()
  }
}

object GreetingsActor{
  def props = Props(classOf[GreetingsActor])
}


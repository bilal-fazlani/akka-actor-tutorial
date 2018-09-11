package IV_forward_example
import IV_forward_example.messages.{Count, CountResponse, Hello}
import akka.actor.{Actor, ActorRef, Props}

class CountActor extends Actor{
  var count:Int = 0
  val greetingsActor:ActorRef = context.actorOf(GreetingsActor.props)

  override def receive: Receive = {
    case h:Hello =>
      count += 1
      //forward does not change sender
      greetingsActor forward h
    case Count() =>
      sender ! CountResponse(count)
    case _ => unhandled()
  }
}

object CountActor{
  def props = Props(classOf[CountActor])
}

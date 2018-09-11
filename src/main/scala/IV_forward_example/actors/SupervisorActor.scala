package IV_forward_example.actors

import IV_forward_example.messages._
import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationDouble

class SupervisorActor extends Actor{

  private val counter: ActorRef = context.actorOf(CountActor.props)

  override def receive: Receive = {
    case x: Hello => counter ! x
    case response: HelloResponse => println(response.text)
    case c: Count =>
      implicit val timeout: Timeout = new Timeout(1 second)
      val future: Future[Any] = counter ? c
      pipe(future) to self
    case countResponse: CountResponse =>
      println(s"count response is ${countResponse.value}")
    case _ => unhandled()
  }
}

object SupervisorActor{
  def props = Props(classOf[SupervisorActor])
}


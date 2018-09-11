package III_piping_example.actors

import III_piping_example.messages._
import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.{ask, pipe}
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationDouble

class SupervisorActor extends Actor{

  private val greeter: ActorRef = context.actorOf(GreetingsActor.props)

  override def receive: Receive = {
    case x: Hello => greeter ! x
    case response: HelloResponse => println(response.text)
    case c: Count =>
      implicit val timeout: Timeout = new Timeout(1 second)
        val future: Future[Any] = greeter ? c
        pipe(future) to self
    case countResponse: CountResponse =>
      println(s"response is $countResponse")
    case _ => unhandled()
  }
}

object SupervisorActor{
  def props = Props(classOf[SupervisorActor])
}


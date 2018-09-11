package II_supervisor_example.actors

import II_supervisor_example.messages._
import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration.DurationDouble
import scala.concurrent.{Await, Future}

class SupervisorActor extends Actor{

  private val greeter: ActorRef = context.actorOf(GreetingsActor.props)

  override def receive: Receive = {
    case x: Hello => greeter ! x
    case response: HelloResponse => println(response.text)
    case c: Count =>
      implicit val timeout: Timeout = new Timeout(1 second)
        val future: Future[Any] = greeter ? c
        val countResponse: CountResponse = Await.result(future, 1 second).asInstanceOf[CountResponse]
        println(s"response is $countResponse")
    case _ => unhandled()
  }
}

object SupervisorActor{
  def props = Props(classOf[SupervisorActor])
}


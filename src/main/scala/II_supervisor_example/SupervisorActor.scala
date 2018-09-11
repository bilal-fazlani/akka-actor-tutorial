package II_supervisor_example
import akka.actor.{Actor, ActorRef, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.DurationDouble

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

case class HelloResponse(text: String)
case class Count()
case class CountResponse(value:Int)
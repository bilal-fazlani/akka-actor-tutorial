package I_hello
import akka.actor.{ActorRef, ActorSystem, PoisonPill}

import scala.concurrent.Await
import scala.concurrent.duration.DurationDouble

object Main extends App {
  val system: ActorSystem = ActorSystem.create("hello-system")
  val actor: ActorRef = system.actorOf(GreetingsActor.props)

  actor ! Hello(4)

  Thread.sleep(2000)

  actor ! PoisonPill

  Await.result(system.terminate(), 2.seconds)
}

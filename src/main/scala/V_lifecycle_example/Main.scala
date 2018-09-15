package V_lifecycle_example

import akka.actor.{ActorRef, ActorSystem, PoisonPill}

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object Main extends App{
  val system:ActorSystem = ActorSystem("lifecycle-example")
  val printer:ActorRef = system.actorOf(LifecyclePrintActor.props)

  printer ! "abc"
  printer ! "error"
  printer ! "abc2"
  printer ! "getCount"

  Thread.sleep(3000)

  printer ! PoisonPill

  Await.result(system.terminate(), 2 seconds)
}

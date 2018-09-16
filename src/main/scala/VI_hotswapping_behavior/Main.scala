package VI_hotswapping_behavior

import VI_hotswapping_behavior.actors.DynamicActor
import VI_hotswapping_behavior.messages.{English, Hello, Hindi, Namaste}
import akka.actor.{ActorRef, ActorSystem, PoisonPill}

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object Main extends App{
  val system: ActorSystem = ActorSystem("hotswapping")
  val actor:ActorRef = system.actorOf(DynamicActor.props)

  actor ! Hello()
  actor ! Namaste()
  actor ! Hindi()
  actor ! Namaste()
  actor ! Hello()
  actor ! English()
  actor ! Hello()

  Thread.sleep(2000)

  actor ! PoisonPill
  Await.result(system.terminate(), 2 seconds)
}

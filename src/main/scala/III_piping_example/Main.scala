package III_piping_example

import akka.actor.{ActorRef, ActorSystem, PoisonPill}

import scala.concurrent.Await
import scala.concurrent.duration.DurationDouble

object Main extends App {
  val actorSystem: ActorSystem = ActorSystem("actor-system")
  val supervisorActor:ActorRef = actorSystem.actorOf(SupervisorActor.props)

  supervisorActor ! Hello(5)
  supervisorActor ! Hello(2)
  supervisorActor ! Hello(3)
  supervisorActor ! Hello()

  supervisorActor ! Count()

  Thread.sleep(2000)

  supervisorActor ! PoisonPill

  Await.result(actorSystem.terminate(), 2 second)
}

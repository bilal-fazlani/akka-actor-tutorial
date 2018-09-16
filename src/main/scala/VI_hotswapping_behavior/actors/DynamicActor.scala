package VI_hotswapping_behavior.actors

import VI_hotswapping_behavior.messages._
import akka.actor.{Actor, Props}

class DynamicActor extends Actor{

  //restarting the actor clears the state of actor

  println(s"constructor")

  def println(value: Any): Unit =  Predef println ("-"*5 + value + "-"*5)

  override def preStart(): Unit = println("preStart")

  override def postStop(): Unit = println("postStop")

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println(s"preRestart with message`${message.get}` and reason `$reason`")
  }

  override def postRestart(reason: Throwable): Unit = {
    println(s"postRestart with reason `$reason`")
  }

  override def receive: Receive = englishBehavior

  def englishBehavior: Receive = {
    case Hello(text) => println(text)
    case Hindi() => context.become(hindiBehavior)
    case _ => println("WARNING: msg unhandled"); unhandled()
  }

  def hindiBehavior: Receive = {
    case Namaste(text) => println(text)
    case English() => context.become(englishBehavior)
    case _ => println("WARNING: msg unhandled"); unhandled()
  }
}

object DynamicActor{
  def props = Props(classOf[DynamicActor])
}


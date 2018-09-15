package V_lifecycle_example

import akka.actor.{Actor, Props}

class LifecyclePrintActor extends Actor{

  //restarting the actor clears the state of actor

  var messageCount = 0
  println(s"constructor. message count is $messageCount")

  def println(value: Any): Unit =  Predef println ("-"*5 + value + "-"*5)

  override def preStart(): Unit = println("preStart")

  override def postStop(): Unit = println("postStop")

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println(s"preRestart with message`${message.get}` and reason `$reason`")
  }

  override def postRestart(reason: Throwable): Unit = {
    println(s"postRestart with reason `$reason`")
  }

  override def receive: Receive = {
    case s:String if s == "error" => {
      messageCount += 1
      println(s"message count is now $messageCount")
      throw new RuntimeException("some error occurred")
    }
    case s:String => {
      messageCount += 1
      println(s"receive message `$s`")
      println(s"message count is now $messageCount")
    }
    case _ => {
      messageCount += 1
      println(s"message count is now $messageCount")
      unhandled()
    }
  }
}

object LifecyclePrintActor{
  def props = Props(classOf[LifecyclePrintActor])
}


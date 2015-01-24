package models

import controllers.Application._
import play.api.libs.iteratee.{Iteratee, Concurrent}
import play.api.mvc.{Action, WebSocket}

import scala.collection.mutable.ListBuffer

import play.api.libs.iteratee.Concurrent._

/**
 * Created by umed on 18.07.14.
 */

object ChatServer {


  val _list=new ListBuffer[Channel[String]];

  def append(channel:Channel[String]){

    _list += channel;

  }

  def notifyAll(message:String){

    _list.map(

      channel=>
        channel push message

    )
  }

  def notifyAll(message:String, senderChannel:Channel[String]){

    _list.map(

      channel=>
        if(channel!=senderChannel)
          channel push message

    )
  }
}

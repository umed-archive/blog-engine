package controllers

import controllers.Application._
import models.ChatServer
import play.api.libs.iteratee.{Iteratee, Concurrent}
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by umed on 18.07.14.
 */
object Chat extends Controller{

  def connect = WebSocket.using[String] { request =>

    val ( out, channel ) = Concurrent.broadcast[String]

    ChatServer append channel

    val in = Iteratee.foreach[String] {

      msg =>

        ChatServer notifyAll (msg, channel)

    }

    (in,out)
  }

  def chat = Action {

    Ok( views.html.chat() )

  }
}

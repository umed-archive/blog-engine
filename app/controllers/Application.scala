package controllers

import models.ChatServer
import play.api._
import play.api.libs.json._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views.js.helper
import scala.util.Random


import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.functional.syntax._
import scala.concurrent.Future


import play.api.libs.iteratee._
import play.api.libs.concurrent.Execution.Implicits.defaultContext


import models._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Главная"))
  }

  def about = Action {
    Ok(views.html.about())
  }

  def editor = Action {
    Ok(views.html.editor())
  }

  def save=Action{

    implicit request =>
      val (header,notice)= form.bindFromRequest.get
      print(header)
      print(notice)
      Ok(views.html.noticePage(notice))
  }


  val form = Form(
    tuple(
    "articletitle"->text,
    "editor"->text
    )
  )

  def add = Action{

    implicit request =>
      val (header,notice)= form.bindFromRequest.get

      Ok(views.html.noticePage(notice))


  }

  def showAll = Action{
    Ok("ok")
  }

}
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
      val noticeInf= form.bindFromRequest.get

      Ok(views.html.noticePage(noticeInf))
  }


  val form = Form(
    "editor"->text
  )

  def add = Action{

    implicit request =>
      val notice= form.bindFromRequest.get

      Ok(views.html.noticePage(notice))


/*      collection.insert(json).map((lastError) => {

        if (lastError.inError)
          Ok("Mongo LastError: %s".format(lastError))
        else
          Ok("User add")

      })*/
  }

  def showAll = Action{
    Ok("ok")
  }

}
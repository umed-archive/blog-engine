package controllers

import play.api._
import anorm._
import play.api.db.DB
import play.api.libs.json._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views.js.helper
import scala.util.Random
import play.api.Play.current

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.functional.syntax._
import scala.concurrent.Future


import play.api.libs.iteratee._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import models._

object Application extends Controller {

  val form = Form(
    tuple(
      "postTitle"->text,
      "editor"->text
    )
  )

  def index = Action {

    val connection = db.DB.getConnection()
    val stmt = connection.createStatement()

    var list:List[(Int,String)] = List()

    try {
      val rs = stmt.executeQuery("select id, title from posts order by id desc")

      while (rs.next())
        list++=List((rs.getInt("id"),rs.getString("title")))
    }
    finally {
      connection.close()
    }
    Ok(views.html.index(list))
  }

  def post(id:Int)=Action{

    val connection = DB.getConnection()

    val stmt = connection.createStatement()

    var notice = Notice("", "")

    try {
      val rs = stmt.executeQuery("select title,post from posts where id="+id)

      rs.next()

      notice = Notice(rs.getString("title"), rs.getString("post"))
    }
    finally {
      connection.close()
    }
    print(notice.post)

    Ok(views.html.post(notice.title,notice.post))
  }

  def about = Action {

    Ok(views.html.about())

  }

  def editor = Action {

    Ok(views.html.editor())

  }

  def save = Action{

    implicit request =>
      val (title,post) = form.bindFromRequest.get
      db.DB.withTransaction( implicit connection =>

        SQL("insert into Posts(title, post) values('" + title + "', E'" + post + "')").executeInsert()

      )
      print(post)
      Ok(views.html.post(title, post))
  }

  def getLastPosts():List[(Int,String)]={

    val connection = db.DB.getConnection()
    val stmt = connection.createStatement()

    var list:List[(Int,String)] = List()

    try {
      val rs = stmt.executeQuery("select id, title from posts order by id desc limit 10")

      while (rs.next())
        list++=List((rs.getInt("id"),rs.getString("title")))
    }
    finally {
      connection.close()
    }
    list
  }
}
package models

import play.api.libs.json.JsObject

/**
 * Created by umed on 15.07.14.
 */
case class Client(firstname: String, lastname: String) {
}

object Client {
  def fromJson(obj: JsObject): Client = Client((obj \ "firstname").toString,
  (obj \ "lastname").toString)
}

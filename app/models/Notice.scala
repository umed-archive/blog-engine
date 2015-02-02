package models

import java.util.Base64


/**
 * Created by umed on 28.01.15.
 */
case class Notice(title:String,post:String) {
}

object Notice{
  def toBase64FromString(str:String):String = Base64.getEncoder().encodeToString(str.toCharArray().map(_.toByte))

  def toStringFromBase64(str:String):String = new String(Base64.getDecoder().decode(str).map(_.toChar))
}
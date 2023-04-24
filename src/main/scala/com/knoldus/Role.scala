package com.knoldus

import play.api.libs.json.{Json, OWrites}

sealed trait Role

case class CEO(role: String) extends Role

object CEO {
  implicit val ceoRole: OWrites[CEO] = Json.writes[CEO]
}

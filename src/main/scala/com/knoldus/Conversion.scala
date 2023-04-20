package com.knoldus

import play.api.libs.json.Json

/* For Case class to Json*/
case class Person(name: String, id: Int, address: String)

/* For Json to Case Class */
case class Employee(name: String, age: Int, id: Int, address: String)

object Conversion {
  /* For Case class to Json*/
  implicit val residentWrites = Json.writes[Person]

  /* For Json to Case Class */
  implicit val residentReads = Json.reads[Employee]
}

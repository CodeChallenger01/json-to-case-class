package com.knoldus

sealed trait Validate

case class StringTooLong(message: String) extends Validate

case class StringContainNumber(message: String) extends Validate

case class StringContainSpecialCharacter(message: String) extends Validate

case class NumberTooLong(message: String) extends Validate

object Check extends App {
  def check(error: Validate): String = {
    error match {
      case StringTooLong(message) => s"Error : $message"
      case StringContainNumber(message) => s"Error : $message"
      case StringContainSpecialCharacter(message) => s"Error : $message"
      case NumberTooLong(message) => s"Error : $message"
    }
  }
}
package com.knoldus

sealed trait Error

case class StringTooLong(message: String) extends Error

case class StringContainNumber(message: String) extends Error

case class StringContainSpecialCharacter(message: String) extends Error

case class NumberTooLong(message: String) extends Error

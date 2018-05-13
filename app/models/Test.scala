package models

import slick.driver.PostgresDriver.api._
import slick.lifted.ProvenShape

import play.api.data.Form
import play.api.data.Forms._

case class Test1(id: Int, field_1: String, field_2: String,
                 field_3: String, field_4: String,field_5: String,
                 field_6: String,field_7: String,field_8: String,
                 field_9: String,field_10: String,field_11: String,
                 field_12: String)

case class Test2(field_13: String, field_14: String, field_15: String,
                 field_16: String, field_17: String,field_18: String,
                 field_19: String,field_20: String,field_21: String,
                 field_22: String,field_23: String,field_24: String,
                 field_25: String)

case class TestRow(test1: Test1, test2: Test2)

class Test(tag: Tag) extends Table[TestRow](tag, "test") {

  def id= column[Int]("id", O.PrimaryKey)

  def field_1= column[String]("field_1")

  def field_2: Rep[String] = column[String]("field_2")

  def field_3: Rep[String] = column[String]("field_3")

  def field_4: Rep[String] = column[String]("field_4")

  def field_5: Rep[String] = column[String]("field_5")

  def field_6: Rep[String] = column[String]("field_6")

  def field_7: Rep[String] = column[String]("field_7")

  def field_8: Rep[String] = column[String]("field_8")

  def field_9: Rep[String] = column[String]("field_9")

  def field_10: Rep[String] = column[String]("field_10")

  def field_11: Rep[String] = column[String]("field_11")

  def field_12: Rep[String] = column[String]("field_12")

  def field_13: Rep[String] = column[String]("field_13")

  def field_14: Rep[String] = column[String]("field_14")

  def field_15: Rep[String] = column[String]("field_15")

  def field_16: Rep[String] = column[String]("field_16")

  def field_17: Rep[String] = column[String]("field_17")

  def field_18: Rep[String] = column[String]("field_18")

  def field_19: Rep[String] = column[String]("field_19")

  def field_20: Rep[String] = column[String]("field_20")

  def field_21: Rep[String] = column[String]("field_21")

  def field_22: Rep[String] = column[String]("field_22")

  def field_23: Rep[String] = column[String]("field_23")

  def field_24: Rep[String] = column[String]("field_24")

  def field_25: Rep[String] = column[String]("field_25")

  private type Test1TupleType = (Int, String, String,  String, String, String, String, String, String, String, String, String, String)
  private type Test2TupleType = (String, String, String,  String, String, String, String, String, String, String, String, String, String)
  private type TestRowTupleType = (Test1TupleType, Test2TupleType)

  private val testShapedValue = (
    (id , field_1, field_2, field_3, field_4, field_5, field_6, field_7, field_8, field_9, field_10, field_11, field_12 ),
    ( field_13, field_14, field_15, field_16, field_17, field_18, field_19, field_20, field_21, field_22, field_23, field_24, field_25)
  ).shaped[TestRowTupleType]

  private val toTestRow: (TestRowTupleType => TestRow) = { testTuple =>
    TestRow(test1 = Test1.tupled.apply(testTuple._1), test2 = Test2.tupled.apply(testTuple._2))
  }

  private val toTestTuple: (TestRow => Option[TestRowTupleType]) = { testRow =>
    Some(Test1.unapply(testRow.test1).get, Test2.unapply(testRow.test2).get)
  }

  def * = testShapedValue <> (toTestRow, toTestTuple)

}


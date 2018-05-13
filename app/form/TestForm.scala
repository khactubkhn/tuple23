package form

import play.api.data.Form
import play.api.data.Forms._

object TestForm {
  val form = Form(
    mapping(
      "test1" -> mapping(
        "id"-> number,
        "field_1" -> text,
        "field_2" -> text,
        "field_3" -> text,
        "field_4" -> text,
        "field_5" -> text,
        "field_6" -> text,
        "field_7" -> text,
        "field_8" -> text,
        "field_9" -> text,
        "field_10" -> text,
        "field_11" -> text,
        "field_12" -> text
      )(Test1.apply)(Test1.unapply),
      "test2" -> mapping(
        "field_13"-> text,
        "field_14" -> text,
        "field_15" -> text,
        "field_16" -> text,
        "field_17" -> text,
        "field_18" -> text,
        "field_19" -> text,
        "field_20" -> text,
        "field_21" -> text,
        "field_22" -> text,
        "field_23" -> text,
        "field_24" -> text,
        "field_25" -> text
      )(Test2.apply)(Test2.unapply)
    )(TestRow.apply)(TestRow.unapply)
  )

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
}

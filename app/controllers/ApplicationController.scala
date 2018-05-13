package controllers

import javax.inject.Inject
import model.{User, UserForm}
import play.api.mvc._

import scala.concurrent.Future
import service.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import models.TestDAO
import models.Test
import models.TestDAO
import play.api.db.slick.DatabaseConfigProvider
import play.api.i18n.{I18nSupport, MessagesApi}

class ApplicationController @Inject() (val testDAO: TestDAO,val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index = Action { implicit request =>
    Ok(views.html.test(form.TestForm.form))
  }

  def addUser() = Action.async { implicit request =>
    UserForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.index(errorForm, Seq.empty[User]))),
      data => {
        val newUser = User(0, data.firstName, data.lastName, data.mobile, data.email)
        UserService.addUser(newUser).map(res =>
          Redirect(routes.ApplicationController.index())
        )
      })
  }

  def addTest() = Action.async { implicit request =>
    form.TestForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.test(errorForm))),
      data => {
        var test1 = models.Test1(data.test1.id, data.test1.field_1, data.test1.field_2, data.test1.field_3, data.test1.field_4, data.test1.field_5, data.test1.field_6, data.test1.field_7, data.test1.field_8, data.test1.field_9, data.test1.field_10, data.test1.field_11, data.test1.field_12)
        var test2 = models.Test2(data.test2.field_13, data.test2.field_14, data.test2.field_15, data.test2.field_16, data.test2.field_17, data.test2.field_18, data.test2.field_19, data.test2.field_20, data.test2.field_21, data.test2.field_22, data.test2.field_23, data.test2.field_24, data.test2.field_25 )
        var testRow = models.TestRow(test1, test2)
        println("DATA" + data)
        println("TEST1" + test1)
        println("TEST2" + test2)
        for {
          r <- testDAO.save(testRow)
        }yield {
          Ok("Add Successful")
        }
      })
  }

  def deleteUser(id: Long) = Action.async { implicit request =>
    UserService.deleteUser(id) map { res =>
      Redirect(routes.ApplicationController.index())
    }
  }
}


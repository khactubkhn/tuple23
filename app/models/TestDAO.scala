package models
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile
import slick.jdbc.JdbcBackend
import slick.lifted.TableQuery

import models.Test

import scala.concurrent.Future

class TestDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider){
  val dbConfig: DatabaseConfig[JdbcProfile] = dbConfigProvider.get[JdbcProfile]
  val db: JdbcBackend#DatabaseDef = dbConfig.db

  import dbConfig.driver.api._

  def save(test: TestRow): Future[Int] = {
    db.run(TestDAO.tests.insertOrUpdate(test))
  }
}

object TestDAO {

  private val tests = TableQuery[Test]

}

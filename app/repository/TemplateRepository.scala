package repository

import javax.inject.{Inject, Singleton}
import models.Template
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class TemplateRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(
  implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class TemplateTable(tag: Tag)
    extends Table[Template](tag, "Template") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def key = column[String]("key")

    def template = column[String]("template")

    def * = (id, key, template) <> ((Template.apply _).tupled, Template.unapply)
  }

  /**
    * The starting point for all queries on the people table.
    */
  private val templateTable = TableQuery[TemplateTable]

  def setup(): Future[Unit] = {
    val schema = templateTable.schema
    db.run(DBIO.seq(schema.create))
  }

  def createTemplate(key: String, template: String): Future[Template] = db.run {

    (templateTable.map(p => (p.key, p.template))
      returning templateTable.map(_.id)

      into ((template, id) => Template(id, template._1, template._2))) += (key, template)
  }

  def getTemplateById(id: Int): Future[Seq[Template]] = db.run {
    templateTable.filter(template => template.id === id).result
  }

  def getTemplateByKey(key: String): Future[Seq[Template]] = db.run {
    templateTable.filter(template => template.key === key).result
  }

}

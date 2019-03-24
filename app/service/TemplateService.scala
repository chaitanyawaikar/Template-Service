package service

import com.google.inject.{Inject, Singleton}
import models.Template
import repository.TemplateRepository
import utils.Constants._
import utils.SetupData._

import scala.concurrent.{ExecutionContext, Future}


@Singleton
class TemplateService @Inject()(templateRepository: TemplateRepository)(implicit ec: ExecutionContext) {


  def getTemplateById(id: Int): Future[Either[String, Template]] = {
    templateRepository.getTemplateById(id).map{
      templateResponse =>
        templateResponse.toList match {
          case _ :: _ => Right(templateResponse.head)
          case _ => Left(TEMPLATE_NOT_FOUND_BY_ID)
        }
    }
  }

  def getTemplateByKey(key: String): Future[Either[String, Template]] = {
    templateRepository.getTemplateByKey(key.toUpperCase).map{
      templateResponse =>
        templateResponse.toList match {
          case _ :: _ => Right(templateResponse.head)
          case _ => Left(TEMPLATE_NOT_FOUND_BY_KEY)
        }
    }
  }

  def setup(): Future[Future[Template]] = {
    templateRepository.setup().map{
      _ =>
       templateRepository.createTemplate("WELCOME",welcomeTemplate)
       templateRepository.createTemplate("NEWSLETTER",newsletterTemplate)
    }
  }
}

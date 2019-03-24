package controllers

import javax.inject.Inject
import models.{ErrorResponse, SuccessResponse, TemplateRequest}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}
import service.TemplateService
import utils.Constants._

import scala.concurrent.{ExecutionContext, Future}

class TemplateController @Inject()(
                                    service: TemplateService,
                                    cc: MessagesControllerComponents)(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  def setup: Action[AnyContent] = Action.async { implicit request =>
    service.setup().map { _ =>
      Ok(Json.toJson("{}"))
    }
  }

  def getTemplateById(id: Int): Action[AnyContent] =
    Action.async { implicit request =>
      service.getTemplateById(id).map {
        case Right(data) =>
          Ok(Json.toJson(data))
        case Left(error) =>
          Ok(Json.toJson(ErrorResponse(NOT_FOUND, error)))
      }
    }

  def getTemplateByKey(key: String): Action[AnyContent] =
    Action.async { implicit request =>
      service.getTemplateByKey(key).map {
        case Right(data) =>
          Ok(Json.toJson(data))
        case Left(error) =>
          Ok(Json.toJson(ErrorResponse(NOT_FOUND, error)))
      }
    }
}

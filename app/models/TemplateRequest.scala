package models

import play.api.libs.json.{Json, OFormat}

case class TemplateRequest(key: String,template: String)


object TemplateRequest {
  implicit val usersRequestsFormat: OFormat[TemplateRequest] = Json.format[TemplateRequest]
}

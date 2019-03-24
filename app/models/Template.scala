package models

import play.api.libs.json.{Json, OFormat}

case class Template(id: Int,key: String,template: String)

object Template {
  implicit val templateFormat: OFormat[Template] = Json.format[Template]
}

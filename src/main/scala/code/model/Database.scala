package code.model

import org.squeryl.Schema
import net.liftweb.squerylrecord.RecordTypeMode._

object Database extends Schema {
  val companies = table[Company]

  //  val companyToEmployees =
  //      oneToManyRelation(companies, employees).via((c,e) => c.id === e.companyId)

  //  on(employees)( e =>
  //    declare(e.companyId is(indexed("idx_employee_companyId")))
  //  )
}
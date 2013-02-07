package code.snippet

import net.liftweb._
import http.{PaginatorSnippet, SHtml}
import util._
import Helpers._
import net.liftweb.http.SHtml._
import code.model.Company
import code.model.Database
import org.squeryl.PrimitiveTypeMode._


object DBCrud extends PaginatorSnippet[Company] {


  def render =
    SHtml.idMemoize(outer =>
      ".hasRecords" #> (if (count == 0) ClearNodes else PassThru) andThen

        ".companies *" #> page.map(company =>
          ".company-name *" #> company.name.toString
        ) &


          ".createRecord [onclick]" #> ajaxInvoke(() => {
            val ftl = Company.createRecord.name("FTL Development " + randomInt(100))
            Database.companies.insert(ftl)

            outer.setHtml
          }) &

          ClearClearable
    )

  override def itemsPerPage = 2

  def count: Long = Database.companies.size

  def page: Seq[Company] = Database.companies.slice(first.toInt, first.toInt + itemsPerPage).toSeq
}


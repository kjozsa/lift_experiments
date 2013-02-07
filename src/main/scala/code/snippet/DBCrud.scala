package code.snippet

import net.liftweb._
import http.{PaginatorSnippet, SHtml}
import util._
import scala.xml.NodeSeq
import Helpers._
import net.liftweb.http.SHtml._
import code.model.Company
import code.model.Database
import org.squeryl.PrimitiveTypeMode._


object DBCrud extends PaginatorSnippet[Company] {


  def render =
    SHtml.idMemoize(outer =>
      ClearClearable &

        ".hasRecords" #> ((ns: NodeSeq) => if (count == 0) NodeSeq.Empty else ns) &

        ".companies *" #> page.map(company =>
          ".company-name *" #> company.name.toString
        ) &

        ".createRecord [onclick]" #> ajaxInvoke(() => {
          val ftl = Company.createRecord.name("FTL Development " + randomInt(100))
          Database.companies.insert(ftl)

          outer.setHtml
        })
    )

  override def itemsPerPage = 2

  def count: Long = Database.companies.size

  def page: Seq[Company] = Database.companies.slice(first.toInt, first.toInt + itemsPerPage).toSeq
}


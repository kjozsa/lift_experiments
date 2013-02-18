package code.snippet

import net.liftweb._
import http.js.JsCmds.Alert
import code.model.Company
import code.model.Database
import org.squeryl.PrimitiveTypeMode._


import _root_.net.liftweb._
import http._
import S._
import SHtml._

import common._
import util._
import Helpers._

import _root_.scala.xml.Text


object DBCrud extends PaginatorSnippet[Company] {

  private object actualRecord extends RequestVar[Box[Company]](Empty)

  def render =
    SHtml.idMemoize(outer =>
      ".hasRecords" #> (if (count == 0) ClearNodes else PassThru) andThen

        ".companies *" #> page.map(company =>
          ".company-name *" #> company.name.toString &

            ".edit" #> link("/dbedit", () => actualRecord(Full(company)), Text("edit")) &

            ".delete" #> ajaxButton(Text("delete"), () => {
              Database.companies.delete(company.id)
              Alert("removed " + company.name) & outer.setHtml
            })
        ) &

          ".createRecord [onclick]" #> ajaxInvoke(() => {
            val ftl = Company.createRecord.name("FTL Development " + randomInt(100))
            Database.companies.insert(ftl)

            outer.setHtml
          }) &

          ClearClearable
    )

  def edit = actualRecord.map(_.toForm(Full("save")) { record =>
    Database.companies.update(record)
    redirectTo("/dbcrud")
  }).openOrThrowException("no user")

  override def itemsPerPage = 2

  def count: Long = Database.companies.size

  def page: Seq[Company] = Database.companies.slice(first.toInt, first.toInt + itemsPerPage).toSeq
}


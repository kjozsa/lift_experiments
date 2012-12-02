package code.model

import net.liftweb.record.{ MetaRecord, Record }
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord
import org.squeryl.annotations.Column

class Company private () extends Record[Company] with KeyedRecord[Long] {

  override def meta = Company

  @Column(name = "id")
  override val idField = new LongField(this)

  val name = new StringField(this, "")
  val description = new OptionalTextareaField(this, 1000)
  val country = new CountryField(this)
  val postCode = new PostalCodeField(this, country)
  val created = new DateTimeField(this)
}
object Company extends Company with MetaRecord[Company]
package com.sopranoworks.bolt

import com.google.cloud.spanner.DatabaseClient

/**
  * Created by takahashi on 2017/03/28.
  */
case class Table(dbClient:DatabaseClient,name:String,columns:List[Column],primaryKey:Index,indexes:Map[String,Index]) {
  private val _columnIndex = columns.map(c => c.name->c.position).toMap

//  def key:String =
//    primaryKey.columns.head.name

//  def isKey(name:String):Boolean = key == name

  def columnTypeOf(columnName:String):Option[String] =
    columns.find(_.name == columnName).map(_.spannerType)

  def column(idx:Int):Column = columns(idx)
  def columnIndexOf(name:String):Option[Int] = _columnIndex.get(name)
}

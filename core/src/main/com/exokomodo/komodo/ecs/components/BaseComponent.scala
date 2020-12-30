package com.exokomodo.komodo.ecs.components

import com.exokomodo.komodo.ecs.entities.Entity

abstract class BaseComponent {
  val id: ComponentId = nextId()

  private var _isEnabled: Boolean = true
  def isEnabled = _isEnabled
  def isEnabled_=(v: Boolean): Unit = { _isEnabled = v }

  private var _isInitialized: Boolean = false
  def isInitialized = _isInitialized
  def isInitialized_=(v: Boolean): Unit = { _isInitialized = v }

  private var _parent: Option[Entity] = None
  def parent: Option[Entity] = _parent
  def parent_=(p: Option[Entity]): Unit = { _parent = p }

  def initialize(): Unit = {
    if (!isInitialized) isInitialized = true
  }

  def isReady: Boolean = (isInitialized, isEnabled, parent) match {
    case (true, true, Some(_)) => true
    case _ => false
  }
}

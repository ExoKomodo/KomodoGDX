package com.exokomodo.komodo.desktop.behaviors

import com.exokomodo.komodo.ecs.components.{BehaviorComponent, TransformComponent, TypeId}
import com.exokomodo.komodo.ecs.entities.Entity

object RotateBehavior {
  def apply(parent: Entity, isEnabled: Boolean = true): RotateBehavior = {
    new RotateBehavior(
      Some(parent),
      isEnabled=isEnabled,
    )
  }
}

@TypeId(id = BehaviorComponent.typeId)
class RotateBehavior(
                      override val parent: Option[Entity],
                      override var isEnabled: Boolean = true,
                    ) extends BehaviorComponent(parent) {
  private var _transform: Option[TransformComponent] = None

  override def initialize(): Unit = {
    super.initialize()

    _transform = parent.get.findComponentByClass(classOf[TransformComponent])
  }

  override def update(delta: Float): Unit = {
    _transform.get.rotation.z += 90 * delta
  }
}

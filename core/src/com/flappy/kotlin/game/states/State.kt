package com.flappy.kotlin.game.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import java.util.logging.Logger

/**
 * Created by Vic on 9/27/2017.
 */
abstract class State(var stateManager: GameStateManager) {
    protected val LOG: Logger = Logger.getLogger(this.javaClass.name)
    var camera: OrthographicCamera = OrthographicCamera()
    var mouse: Vector2 = Vector2()

    abstract fun handleInput()
    abstract fun update(dt: Float)
    abstract fun render(sb: SpriteBatch)
    abstract fun dispose()


    protected fun OrthographicCamera.setToOrtho(x: Int, y: Int) = this.setToOrtho(false, x.toFloat(), y.toFloat())
}

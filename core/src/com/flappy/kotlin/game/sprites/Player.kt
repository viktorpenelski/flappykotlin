package com.flappy.kotlin.game.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

/**
 * Created by Vic on 9/28/2017.
 */
class Player(x: Float, y: Float) {
    companion object {
        private const val GRAVITY = -15F
        private const val MOVEMENT = 100
    }

    private val texture = Texture("birdanimation.png")
    private val playerAnimation = Animation(texture, 3, 0.5F)
    private val flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"))
    private var velocity = Vector2(0F, 0F)
    val position = Vector2(x, y)
    val bounds = Rectangle(x, y, texture.width.toFloat() / 3, texture.height.toFloat())

    fun update(dt: Float) {
        playerAnimation.update(dt)
        if (position.y > 0) {
            velocity.add(0F, GRAVITY)
        }
        velocity.scl(dt)
        position.add(MOVEMENT * dt, velocity.y)

        if (position.y < 0) {
            position.y = 0F
        }

        velocity.scl(1/dt)
        bounds.setPosition(position.x, position.y)
    }

    fun jump() {
        velocity.y = 250F
        flap.play(0.1F)
    }

    fun getTexture() = playerAnimation.getFrame()

    fun dispose() {
        texture.dispose()
        flap.dispose()
    }


}
package com.flappy.kotlin.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import java.util.*


class Tube(x: Float) {
    companion object {
        const val TUBE_WIDTH = 52
        private const val FLUCTUATION = 130
        private const val TUBE_GAP = 100
        private const val LOWEST_OPENING = 120
    }

    private val random = Random()

    val topTube = Texture("toptube.png")
    val bottomTube = Texture("bottomtube.png")
    val posTopTube = Vector2(x, (random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING).toFloat())
    val posBotTube = Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.height)
    var scored = false

    private val boundsTop = Rectangle(posTopTube.x, posTopTube.y, topTube.width.toFloat(), topTube.height.toFloat())
    private val boundsBot = Rectangle(posBotTube.x, posBotTube.y, bottomTube.width.toFloat(), bottomTube.height.toFloat())

    fun reposition(x: Float) {
        posTopTube.set(x, (random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING).toFloat())
        posBotTube.set(Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.height))
        boundsTop.setPosition(posTopTube.x, posTopTube.y)
        boundsBot.setPosition(posBotTube.x, posBotTube.y)
        scored = false
    }

    fun collides(player: Rectangle) = player.overlaps(boundsTop) || player.overlaps(boundsBot)

    fun dispose() {
        topTube.dispose()
        bottomTube.dispose()
    }

}
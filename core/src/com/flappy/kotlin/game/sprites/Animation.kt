package com.flappy.kotlin.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

/**
 * Created by Vic on 10/21/2017.
 */
class Animation(texture: Texture, private val frameCount: Int, cycleTime: Float) {
    private val region = TextureRegion(texture)
    private val frames = Array<TextureRegion>()
    private val maxFrameTime = cycleTime / frameCount
    private var frame = 0
    private var currentFrameTime = 0F

    init {
        val frameWidth = region.regionWidth / frameCount
        for (i in 0 until frameCount) {
            frames.add(TextureRegion(region, i * frameWidth, 0, frameWidth, region.regionHeight))
        }

    }

    fun update(dt: Float) {
        currentFrameTime += dt
        if (currentFrameTime > maxFrameTime) {
            frame++
            currentFrameTime = 0F
        }
        if (frame >= frameCount) {
            frame = 0
        }
    }

    fun getFrame(): TextureRegion = frames.get(frame)


}
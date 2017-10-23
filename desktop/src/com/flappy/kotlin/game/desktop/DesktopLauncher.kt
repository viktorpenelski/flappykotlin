package com.flappy.kotlin.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.flappy.kotlin.game.FlappyKotlinGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = FlappyKotlinGame.WIDTH
        config.height = FlappyKotlinGame.HEIGHT
        config.title = FlappyKotlinGame.TITLE

        LwjglApplication(FlappyKotlinGame(), config)
    }
}

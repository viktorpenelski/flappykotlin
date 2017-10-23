package com.flappy.kotlin.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.flappy.kotlin.game.states.GameStateManager
import com.flappy.kotlin.game.states.MenuState

/**
 * Created by Vic on 9/27/2017.
 */
class FlappyKotlinGame : ApplicationAdapter() {
    companion object {
        const val WIDTH: Int = 480
        const val HEIGHT: Int = 800
        const val TITLE = "Flappy Kotlin"
    }

    private lateinit var img: Texture
    private lateinit var gameStateManager: GameStateManager
    private lateinit var batch: SpriteBatch
    private lateinit var music: Music

    override fun create() {
        gameStateManager = GameStateManager()
        batch = SpriteBatch()
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        gameStateManager.push(MenuState(gameStateManager))
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"))
        music.isLooping = true
        music.volume = 0.15F
        music.play()
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gameStateManager.update(Gdx.graphics.deltaTime)
        gameStateManager.render(batch)
    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
        music.dispose()
    }
}
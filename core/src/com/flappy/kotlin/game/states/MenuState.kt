package com.flappy.kotlin.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.flappy.kotlin.game.FlappyKotlinGame

/**
 * Created by Vic on 9/27/2017.
 */
class MenuState(stateManager: GameStateManager) : State(stateManager) {
    private var background: Texture
    private var playButton: Texture

    init {
        camera.setToOrtho(FlappyKotlinGame.WIDTH / 2, FlappyKotlinGame.HEIGHT / 2)
        background = Texture("bg.png")
        playButton = Texture("playbtn.png")
    }

    override fun handleInput() {
        if(Gdx.input.justTouched()) {
            stateManager.set(PlayState(stateManager))
        }
    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = camera.combined
        sb.begin()
        sb.draw(background, 0F, 0F)
        sb.draw(playButton, (camera.position.x - playButton.width / 2), camera.position.y)
        sb.end()
    }

    override fun dispose(){
        background.dispose()
        playButton.dispose()
        LOG.info("MenuState disposed")
    }
}
package com.flappy.kotlin.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.flappy.kotlin.game.FlappyKotlinGame
import com.flappy.kotlin.game.sprites.Player
import com.flappy.kotlin.game.sprites.Tube


/**
 * Created by Vic on 9/27/2017.
 */
class PlayState(gameStateManager: GameStateManager) : State(gameStateManager) {
    companion object {
        private const val TUBE_SPACING = 125
        private const val TUBE_COUNT = 4
        private const val CAM_OFFSET = 80
        private const val GROUND_Y_OFFSET = -50F
    }

    private val background = Texture("bg.png")
    private val player = Player(50F, 100F)
    private val tubes = Array<Tube>()
    private val ground = Texture("ground.png")
    private val groundPos1 = Vector2((camera.position.x - camera.viewportWidth / 2), GROUND_Y_OFFSET)
    private val groundPos2 = Vector2((camera.position.x - camera.viewportWidth / 2) + ground.width, GROUND_Y_OFFSET)
    private var score = 0
    private var font = BitmapFont()

    init {
        camera.setToOrtho(FlappyKotlinGame.WIDTH / 2, FlappyKotlinGame.HEIGHT / 2)
        for (i in 1..TUBE_COUNT) {
            tubes.add(Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH).toFloat()))
        }
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            player.jump()
        }
    }

    override fun update(dt: Float) {
        handleInput()
        updateGround()
        player.update(dt)
        camera.position.x = player.position.x + CAM_OFFSET
        updateTubes()

        if (player.position.y <= ground.height + GROUND_Y_OFFSET) {
            stateManager.set(MenuState(stateManager))
        }
        camera.update()
    }

    private fun updateTubes() {
        for (tube in tubes) {
            repositionOffScreenTube(tube)
            scorePoint(tube)
            if (tube.collides(player.bounds)) {
                stateManager.set(MenuState(stateManager))
                break
            }
        }
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = camera.combined
        sb.begin()
        sb.draw(background, camera.position.x - (camera.viewportWidth / 2), 0F)
        sb.draw(player.getTexture(), player.position.x, player.position.y)
        sb.renderTubes()
        sb.draw(ground, groundPos1.x, groundPos1.y)
        sb.draw(ground, groundPos2.x, groundPos2.y)
        sb.renderScore()
        sb.end()
    }

    private fun SpriteBatch.renderTubes() {
        for (tube in tubes) {
            this.draw(tube.topTube, tube.posTopTube.x, tube.posTopTube.y)
            this.draw(tube.bottomTube, tube.posBotTube.x, tube.posBotTube.y)
        }
    }

    private fun SpriteBatch.renderScore() {
        val relativeVector = relativeVector(5F, 15F, camera)
        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        font.color = Color.WHITE
        font.setUseIntegerPositions(false)
        font.draw(this, "Score: $score", relativeVector.x, relativeVector.y)
    }

    private fun repositionOffScreenTube(tube: Tube) {
        if (camera.position.x - (camera.viewportWidth / 2) > tube.posTopTube.x + tube.topTube.width) {
            tube.reposition(tube.posTopTube.x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT)
        }
    }

    private fun scorePoint(tube: Tube) {
        if (player.position.x >= (tube.posTopTube.x + tube.topTube.width) && !tube.scored) {
            score++
            tube.scored = true
            LOG.info("Score: $score")
        }
    }

    override fun dispose() {
        background.dispose()
        player.dispose()
        ground.dispose()
        font.dispose()
        for (tube in tubes) {
            tube.dispose()
        }
        LOG.info("PlayState dispose")
    }

    private fun updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.width) {
            groundPos1.add((ground.width * 2).toFloat(), 0F)
        }
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.width) {
            groundPos2.add((ground.width * 2).toFloat(), 0F)
        }
    }

    private fun relativeVector(x: Float, y: Float, c: OrthographicCamera) =
            Vector2(x + c.position.x - c.viewportWidth / 2, y + c.position.y - c.viewportHeight / 2)
}

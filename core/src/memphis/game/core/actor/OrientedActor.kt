package memphis.game.core.actor

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import memphis.game.core.Environment
import memphis.game.core.NamedAnimation


abstract class OrientedActor(animations : List<NamedAnimation>, environment: Environment) : Actor(animations, environment) {

    companion object {
        val animationMap = mapOf(
                Item.Orientation.RIGHT to "side",
                Item.Orientation.LEFT to "side",
                Item.Orientation.DOWN to "front",
                Item.Orientation.UP to "back"
        )
    }

    override val baseType: BaseType = BaseType.CENTRIC

    var orientation = Item.Orientation.RIGHT

    override fun renderFrame(batch: SpriteBatch, currentFrame : TextureRegion) : TextureRegion {
        size.set(
                currentFrame.regionWidth.toFloat(),
                currentFrame.regionHeight.toFloat()
        )
        baseY = size.x/3
        baseX = size.x/1.4f
        //TODO: should be replaced with render method parameters
        if (orientation == Orientation.LEFT) {
            currentFrame.flip(true, false)
        }
        batch.draw(
                currentFrame,
                //position, bottom left corner
                position.x - (size.x / 2),
                position.y,
                size.x,
                size.y
        )
        if (currentFrame.isFlipX ) {
            currentFrame.flip(true, false)
        }
        return currentFrame
    }

    override fun updateAction(action: Action) {
        if(action.orientation != null) {
            this.orientation = action.orientation
        }
        super.updateAction(action)
    }

    override fun getAnimation(action : Action) : NamedAnimation {
        return animations.find { it.name == action.type.name.toLowerCase()+"-"+ animationMap[this.orientation]}
                ?: throw Exception("No animation ${action.type.name} for Actor $this")
    }
}
package memphis.game.core

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion


class NamedAnimation(val name : String, val breakable : Boolean = false, frameDuration : Float, keyFrames : Array<out TextureRegion>) : Animation(frameDuration, *keyFrames) {}
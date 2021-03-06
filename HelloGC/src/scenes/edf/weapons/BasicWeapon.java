package scenes.edf.weapons;

import java.awt.Color;

import texture.Texture;
import texture.text.TextTextureMaker;
import classes.character.shooting.ShootingCharacter;
import classes.character.shooting.ShootingWeaponCharacter;
import classes.scene.ShootingScene;
import common.LR;

public class BasicWeapon extends ShootingWeaponCharacter {
	static final float DISTANCE_FROM_OWNER = 8;
	static final int SHOOT_DELAY_FRAME = 18;
	public static final int MAX_CHARGE = 50;
	public static final Texture WEAPON_TEXTURE = TextTextureMaker.createText("↑");

	public BasicWeapon(ShootingScene scene, ShootingCharacter owner, LR equipLR) {
		super(scene, owner, equipLR);
		setTexture(WEAPON_TEXTURE);
		setHeight(20);
		setWidth(10);
		setColor(Color.white);
		setTeam(owner.getTeam());
	}

	@Override
	protected float getDistanceFromOwner() {
		return DISTANCE_FROM_OWNER;
	}

	@Override
	protected int getShootDelayFrame() {
		return SHOOT_DELAY_FRAME;
	}

	@Override
	public int getMaxCharge() {
		return MAX_CHARGE;
	}
}

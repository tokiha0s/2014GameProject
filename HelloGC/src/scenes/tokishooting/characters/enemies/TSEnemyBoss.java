package scenes.tokishooting.characters.enemies;

import static common.Commons.*;

import java.awt.Color;

import main.FPSManager;
import scenes.tokishooting.TSScene;
import texture.Texture;
import texture.TextureLoader;
import classes.scene.ShootingScene;
import common.Commons;
import common.LR;

public class TSEnemyBoss extends TSEnemyBase {
	private static final int POWER = 1;
	private static final int HP = 120;
	private static final int SIZE = 100;
	private static final Texture TEXTURE = TextureLoader.loadTexture(Commons.IMAGE_FOLDER_STRING
			+ "dotTokiIcon.png");
	private static final float ROTATE_SPEED = 0.2f;
	private static final float FALL_SPEED = 0.3f;
	private static final int MONEY = 300;
	private static final TSEnemyProperty property = new TSEnemyProperty(
			POWER, HP, SIZE, TEXTURE, ROTATE_SPEED, FALL_SPEED, MONEY);

	public TSEnemyBoss(ShootingScene parentScene, float bornAngle, LR lr) {
		super(parentScene, bornAngle, lr, property);
	}

	@Override
	protected boolean updateProcess() {
		if (getOffsetY() < WIDTH / 2) {
			setVOffsetY(getVOffsetY() * 0.95f);
		}
		// 定期的に中ボスを生み出す
		if (FPSManager.totalFrame() % 900 == 0) {
			((TSScene) getParentScene()).add(new TSEnemyToki(
					getParentScene(), getAngle(), LR.RIGHT));
		}
		return super.updateProcess();
	}

	@Override
	protected Color createColor() {
		return new Color(1f, 0.1f, 0.3f);
	}

}
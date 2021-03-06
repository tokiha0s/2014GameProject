package scenes.edf;

import java.lang.reflect.InvocationTargetException;

import scenes.edf.enemies.EDFEnemy;
import classes.scene.ShootingScene;

import common.LR;

/**
 * 敵の発生タイミングと発生場所の定義
 * 
 * @author shirakawa
 *
 */
public class SpawnData {
	private Class<? extends EDFEnemy> enemyClass;
	private long spawnFrame;
	private float angle;
	private LR rotateLR;

	public SpawnData(Class<? extends EDFEnemy> enemyClass, long spawnFrame,
			float angle, LR rotateLR) {
		this.enemyClass = enemyClass;
		this.spawnFrame = spawnFrame;
		this.angle = angle;
		this.rotateLR = rotateLR;
	}

	public EDFEnemy spawnTo(ShootingScene parentScene) {
		try {
			return enemyClass.getConstructor(ShootingScene.class, float.class,
					LR.class)
					.newInstance(parentScene, angle, rotateLR);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			System.err.println("エラー：コンストラクタが呼び出せません");
			e.printStackTrace();
		}
		return null;
	}

	public boolean canSpawn(long frame) {
		return this.spawnFrame == frame;
	}
}

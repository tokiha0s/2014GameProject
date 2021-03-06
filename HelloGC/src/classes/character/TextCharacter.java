package classes.character;

import texture.text.FontDef;
import texture.text.TextTextureMaker;

/**
 * テキストテクスチャのみを持つシンプルなキャラクター
 * 
 * @author shirakawa
 * 
 */
public class TextCharacter extends GameCharacterObjectImpl {
	private FontDef fontDef = FontDef.DEFAULT;
	private String currentText = "";

	public TextCharacter() {
		//
	}

	public TextCharacter(String text) {
		updateText(text);
	}

	public TextCharacter(String text, FontDef fontDef) {
		this.fontDef = fontDef;
		updateText(text);
	}

	public void updateText(String text) {
		if (currentText.equals(text)) {
			return;
		}
		currentText = text;
		if (getTexture() != null) {
			getTexture().dispose();
		}
		setTexture(TextTextureMaker.createText(text, fontDef));
		resetSize();
	}

	public void resetSize() {
		setWidth(getTexture().getWidth());
		setHeight(getTexture().getHeight());
	}

}

package texture;

import static main.Commons.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TextTexture {
	private static Font font = null;
	private static final String FONT_FILEPATH = RESOURCE_FOLDER_STRING
			+ "Ricty.ttf";
	private static final int FONT_HEIGHT = 24;

	/**
	 * 外部フォントで表示したい文字列を書いたテクスチャーを生成する
	 */
	public Texture createTextTexture(String str, int width, int height) {

		BufferedImage image = null;
		Graphics2D g = null;
		try {
			if (font == null)
				createFont();
			image = new TextureLoader().createImageData(width * 2, height * 2);

			// 透明色で塗りつぶし、BufferedImage を初期化する
			g = image.createGraphics();

			// 外部フォントを使う準備をする
			g.setFont(font);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.black);

			// 表示する文字列の位置を計算する
			String message = str;
			int count = 0;
			int y = 0;
			while (0 < message.length()) {
				boolean isDrawed = false;

				if (message.length() <= count) {
					isDrawed = true;
				} else {
					// メッセージウインドウの右端まで文字を書き込んだら、改行する
					int awidth = g.getFontMetrics().stringWidth(
							message.substring(0, count + 1));

					isDrawed = (width < awidth);
				}

				if (isDrawed) {
					g.drawString(message.substring(0, count), 0, FONT_HEIGHT);
					message = message.substring(count);
					y += FONT_HEIGHT + 6;
					count = 0;
				} else {
					count++;
				}
			}

			return new TextureLoader().loadTexture(image);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (g != null) {
				g.dispose();
			}
			if (image != null) {
				image.flush();
			}
		}
		return null;
	}

	private void createFont() {
		try {
			InputStream is = new FileInputStream(FONT_FILEPATH);
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(
					(float) FONT_HEIGHT);
			is.close();
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
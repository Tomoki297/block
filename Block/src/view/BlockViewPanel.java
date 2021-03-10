package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

import model.BlockBean;

public class BlockViewPanel extends Panel{

	private BlockBean saveImageInformation;
	private Graphics offScreanGraphics;
	private Image offScreanImageArea;

	//オフスクリーン用の描画領域を作成
	@Override
	public void setBounds(int x, int y, int width, int height) {

		//親クラスの境界セッター
		super.setBounds(x, y, width, height);
		//画面幅と高さの取得
		int nowWidth = this.getWidth();
		int nowHeight = this.getHeight();

		//描画領域作成
		offScreanImageArea = this.createImage(nowWidth, nowHeight);
		offScreanGraphics = offScreanImageArea.getGraphics();

	}

	//画像情報を渡して引き換え
	public void changeImageInformation(BlockBean b) {

		saveImageInformation = b;
		this.repaint();

	}

	//ダブルバッファを行う
	@Override
	public void update(Graphics g) {
		//画像情報Beanに従って描画を呼び出し
		drawing(g);

	}

	//画像情報Beanに従って描画
	public void drawing(Graphics g) {

		//条件1
		if (saveImageInformation != null) {
			try {
			//オフスクリーンの描画をいったん消去
			offScreanGraphics.clearRect(0, 0, saveImageInformation.getDrawingRangeWidth(), saveImageInformation.getDrawingRangeHeight());

			//ボールの描画
			offScreanGraphics.drawImage(saveImageInformation.getBallImage(), saveImageInformation.getBallCenterX(), saveImageInformation.getBallCenterY(), saveImageInformation.getBallWidth() * 2, saveImageInformation.getBallHeight() * 2, this);

			//カーソルの描画
			offScreanGraphics.drawImage(saveImageInformation.getRacketImage(), saveImageInformation.getRacketX(), saveImageInformation.getRacketY(), saveImageInformation.getRacketWidth(), saveImageInformation.getRacketHeight(), this);

			//繰り返し1
			for (int count = 0; count < saveImageInformation.getBlockExist().length; count++) {

			//条件2
				if (saveImageInformation.getBlockExist()[count] == 1) {
					//ブロックを描く
					offScreanGraphics.drawImage(saveImageInformation.getBlockImage(), saveImageInformation.getBlockX()[count], saveImageInformation.getBlockY()[count], 50 , 20 , this);
				}
			}
			//オフスクリーンイメージ領域描画
			g.drawImage(offScreanImageArea, 0 , 0, this);

			//条件3
			if (saveImageInformation.getBlockQuantity() == 0) {

				//クリア画面を描画
				Font font = new Font("Serif", Font.PLAIN, 50);
				g.setFont(font);
				g.setColor(Color.blue.darker());
				g.drawString("クリアだよ！！", 100, 200);
			}
			//条件4
			if (saveImageInformation.getState() == 2) {

				//ゲームオーバー画面を描画
				Font font = new Font("Serif", Font.PLAIN, 50);
				g.setFont(font);
				g.setColor(Color.blue.darker());
				g.drawString("残念...ゲームオーバー...", 20, 200);
			}
			}catch (NullPointerException e) {
				System.out.print("失敗");
			}
		}


	}
}

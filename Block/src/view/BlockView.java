package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import controller.BlockControl;
import model.BlockBean;


public class BlockView extends Frame implements ActionListener, MouseMotionListener{

	private BlockViewPanel drawingArea;
	private BlockControl controlThread;
	private Button startButton;
	private Button endButton;
	private Button restartButton;
	private BlockBean imageInformation = new BlockBean();

	public BlockBean getImageInformation() {
		return imageInformation;
	}
	public void setImageInformation(BlockBean imageInformation) {
		this.imageInformation = imageInformation;
	}

	public BlockView() {

		//描画エリア、パネル設定
		drawingArea = new BlockViewPanel();
		drawingArea.setBackground(Color.black);
		drawingArea.setVisible(true);
		this.add(drawingArea);

		//ボタン設定
		startButton = new Button("開始");
		endButton = new Button("終了");
		restartButton = new Button("再開");
		this.add(startButton);
		this.add(endButton);
		this.add(restartButton);

		//リスナーとして登録設定
		startButton.addActionListener(this);
		endButton.addActionListener(this);
		restartButton.addActionListener(this);
		drawingArea.addMouseMotionListener(this);

		//初期情報セット
		ini();

	}

	public void ini() {

		//画像情報のセット
		imageInformation.setBallImage(Toolkit.getDefaultToolkit().getImage("src/ball.jpg"));
		imageInformation.setRacketImage(Toolkit.getDefaultToolkit().getImage("src/racket.jpg"));
		imageInformation.setBlockImage(Toolkit.getDefaultToolkit().getImage("src/block.jpg"));

		//ボールの初期位置
		imageInformation.setBallCenterX(10);
		imageInformation.setBallCenterY(100);

		//ボールの縦、横幅
		imageInformation.setBallWidth(5);
		imageInformation.setBallHeight(5);

		//実行時間間隔
		imageInformation.setBallSpeed(5);

		//次のボールの増分量
		imageInformation.setBallNextX(2);
		imageInformation.setBallNextY(2);

		//ブロックの縦、横幅と間隔
		imageInformation.setBlockWidth(50);
		imageInformation.setBlockHeight(20);
		imageInformation.setBlockMargin(10);

		//ブロックの行列
		imageInformation.setBlockRow(5);
		imageInformation.setBlockColumn(3);
		imageInformation.blockset();

		//モード
		imageInformation.setState(0);
		//0 = 初期状態, 1 = 実行中, 2 = ボールが落ちた, 3 = ブロックが全消
	}

	public void setViewInfo() {

		int buttonHeight = 30;

		//見える情報の取得
		Insets insets = this.getInsets();
		int widthRange = this.getWidth() - insets.left - insets.right;
		int heightRange = this.getHeight() - insets.top - insets.bottom - buttonHeight;

		//インセットに合わせて描画エリアを配置
		drawingArea.setBounds(insets.left, insets.top, widthRange, heightRange);

		//ボタンの幅設定
		int buttonWidth = widthRange / 3 ;

		//ボタンの配置の位置を計算
		int basicButtonX = insets.left;
		int basicButtonY = this.getHeight() - insets.bottom - buttonHeight;

		//ボタンの配置
		startButton.setBounds(basicButtonX, basicButtonY, buttonWidth, buttonHeight);
		endButton.setBounds(basicButtonX + buttonWidth, basicButtonY, buttonWidth, buttonHeight);
		restartButton.setBounds(basicButtonX + buttonWidth * 2, basicButtonY, buttonWidth, buttonHeight);

		//ラケットの幅と高さをセット
		imageInformation.setRacketWidth(50);
		imageInformation.setRacketHeight(10);

		//ラケットの位置情報をセット
		imageInformation.setRacketX((widthRange - imageInformation.getRacketWidth()) / 2);
		imageInformation.setRacketY((heightRange - imageInformation.getRacketHeight()));

		//描画領域幅と高さの情報をセット
		imageInformation.setDrawingRangeWidth(drawingArea.getWidth());
		imageInformation.setDrawingRangeHeight(heightRange);


	}

	//ブロックがなくなったら終了
	public void end() {

		controlThread.interrupt();

	}

	//描画処理
	public void draw(BlockBean b) {

		drawingArea.changeImageInformation(b);

	}

	//開始ボタン
	public void startButton() {

		//条件1
		if( imageInformation.getState() == 0) {
			imageInformation.setState(1);
			controlThread = new BlockControl(this);
			controlThread.start();

		}
	}

	//終了ボタン
	public void endButton() {

		//強制終了
		System.exit(0);
	}

	//再開ボタン
	public void restartButton() {

		//条件2
		if (imageInformation.getState() == 2 || imageInformation.getState() == 3) {
			ini();
			startButton();
		}

	}

	//配置ボタンの実行処理
	public void button(ActionEvent a) {

		//条件3
		if( a.getSource().equals(startButton)) {
			startButton();
		//条件4
		} else if (a.getSource().equals(endButton)) {
			endButton();
		//条件5
		} else if(a.getSource().equals(restartButton)) {
			restartButton();
		}
	}

	//空実装
	public void mouseDragged(MouseEvent e) {

	}

	//マウスが移動した処理
	public void mouseMove(MouseEvent m) {

		int mousePoint;

		//条件6
		if (m.getX() + imageInformation.getRacketWidth() > imageInformation.getDrawingRangeWidth()) {

			//領域内に収まるように、マウスの位置を保持
			mousePoint = m.getX();

		} else {

			//マウス移動分をマウス位置に保持
			mousePoint = imageInformation.getDrawingRangeWidth() - imageInformation.getRacketWidth();

		}

		//ラケットの座標をセット
		imageInformation.setRacketX(mousePoint);

		//描画処理
		draw(imageInformation);


	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}

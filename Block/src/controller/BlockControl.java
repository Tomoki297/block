package controller;

import model.BlockBean;
import model.BlockModel;
import view.BlockView;

public class BlockControl extends Thread{

	private BlockModel model;
	private BlockView view;
	private BlockBean bean;

	public BlockControl(BlockView b) {

		//このクラスのviewに情報を代入
		this.view = b;
		//このクラスのbeanにviewが保持している情報を代入
		this.bean = view.getImageInformation();
		//このクラスのmodelをインスタンス生成
		this.model = new BlockModel();

	}

	//ブロック制御用スレッド
	public void thread() throws InterruptedException {

		view.draw(bean);
		Thread.sleep(100);

		//無限ループ
		for (int i = 0; i < 1;) {

			//現在の画像の情報を取得
			bean = view.getImageInformation();

			//次の情報を取得
			bean = model.judge(bean);

			//新しい情報を更新
			view.setImageInformation(bean);

			//新しい情報を更新
			view.draw(bean);

			//条件1
			if (bean.getBlockQuantity() == 0) {

				//状態変更
				bean.setState(3);
				//終了
				view.end();
			//条件2
			} else if (bean.getState() == 2) {

				//終了
				view.end();

			}

			//一時停止
			Thread.sleep(bean.getBallSpeed());

		}
	}


}

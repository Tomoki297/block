package model;

public class BlockModel {

	public BlockBean judge(BlockBean b) {

		int ballCenterX = b.getBallCenterX();
		int ballCenterY = b.getBallCenterY();
		int ballWidth = b.getBallWidth();
		int ballHeight = b.getBallHeight();
		int racketX = b.getRacketX();
		int racketY = b.getRacketY();
		int racketWidth = b.getRacketWidth();
		int racketHeight = b.getRacketHeight();
		int drawingRangeWidth = b.getDrawingRangeWidth();
		int drawingRangeHeight = b.getDrawingRangeHeight();
		int ballNextX = b.getBallNextX();
		int ballNextY = b.getBallNextY();
		int arySize = b.getBlockColumn() * b.getBlockRow();

		//条件1
		if (ballCenterY + ballWidth >= racketY && ballCenterY + ballHeight <= racketY + racketWidth && ballCenterX + ballWidth >= racketX && ballCenterX <= racketX + racketWidth) {
			ballNextY = -2;
			//条件2
			if (ballCenterX < racketX || ballCenterX + ballWidth > racketX + racketWidth) {
				//条件3
				if (ballNextX == 0) {
					//条件4
					if (ballNextX < racketX) {
						ballNextX = -2;
					}else {
						//条件5
						if (ballCenterX + ballWidth > racketX + racketWidth) {
							ballNextX = 2;
						}
					}
				}
			}
		}else {
			//条件6
			if (ballCenterX < 0) {
				ballNextX = 2;
			}
			//条件7
			if (ballCenterX + ballWidth > drawingRangeWidth) {
				ballNextX = -2;
			}
			//条件8
			if (ballCenterY < 0) {
				ballNextY = 2;
			}
		}
		//繰り返し1
		for (int count = 0; count < arySize; count++) {
			//条件9
			if (b.getBlockExist()[count] == 1) {
				//条件10
				if(ballCenterY + ballWidth >= b.getBlockY()[count] && ballCenterY <= b.getBlockY()[count] + b.getBlockHeight() && ballCenterX + ballWidth >= b.getBlockX()[count] && ballCenterX <= b.getBlockX()[count] + b.getBlockWidth()) {
					ballNextY = -ballNextY;
					//
					b.setBlockExist(count);
					b.setBlockQuantity(-1);
				}
			}
		}
		//条件11
		if (ballCenterY + ballHeight > drawingRangeHeight + 100) {
			b.setState(2);
		}

		//ボールの位置変更
		ballCenterX = ballCenterX + ballNextX;
		ballCenterY = ballCenterY + ballNextY;

		//次のボールをセット
		b.setBallCenterX(ballCenterX);
		b.setBallCenterY(ballCenterY);

		//次のボール増分量をセット
		b.setBallNextX(ballNextX);
		b.setBallCenterY(ballCenterY);

		return b;
	}
}

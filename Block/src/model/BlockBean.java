package model;

import java.awt.Image;

public class BlockBean {

	private Image ballImage;
	private Image racketImage;
	private Image blockImage;
	private int ballSpeed;
	private int ballWidth;
	private int ballHeight;
	private int ballCenterX;
	private int ballCenterY;
	private int ballNextX;
	private int ballNextY;
	private int drawingRangeWidth;
	private int drawingRangeHeight;
	private int racketX;
	private int racketY;
	private int racketWidth;
	private int racketHeight;
	private int[] blockExist;
	private int[] blockX;
	private int[] blockY;
	private int blockWidth;
	private int blockHeight;
	private int blockMargin;
	//行
	private int blockRow;
	//列
	private int blockColumn;
	private int blockQuantity;
	private int state;

	public Image getBallImage() {
		return ballImage;
	}

	public Image getRacketImage() {
		return racketImage;
	}

	public Image getBlockImage() {
		return blockImage;
	}

	public int getBallSpeed() {
		return ballSpeed;
	}

	public int getBallWidth() {
		return ballWidth;
	}

	public int getBallHeight() {
		return ballHeight;
	}

	public int getBallCenterX() {
		return ballCenterX;
	}

	public int getBallCenterY() {
		return ballCenterY;
	}

	public int getBallNextX() {
		return ballNextX;
	}

	public int getBallNextY() {
		return ballNextY;
	}

	public int getDrawingRangeWidth() {
		return drawingRangeWidth;
	}

	public int getDrawingRangeHeight() {
		return drawingRangeHeight;
	}

	public int getRacketX() {
		return racketX;
	}

	public int getRacketY() {
		return racketY;
	}

	public int getRacketWidth() {
		return racketWidth;
	}

	public int getRacketHeight() {
		return racketHeight;
	}

	public int[] getBlockExist() {
		return blockExist;
	}

	public int[] getBlockX() {
		return blockX;
	}

	public int[] getBlockY() {
		return blockY;
	}

	public int getBlockWidth() {
		return blockWidth;
	}

	public int getBlockHeight() {
		return blockHeight;
	}

	public int getBlockMargin() {
		return blockMargin;
	}

	public int getBlockRow() {
		return blockRow;
	}

	public int getBlockColumn() {
		return blockColumn;
	}

	public int getBlockQuantity() {
		return blockQuantity;
	}

	public int getState() {
		return state;
	}

	public void setBallImage(Image ballImage) {
		this.ballImage = ballImage;
	}

	public void setRacketImage(Image racketImage) {
		this.racketImage = racketImage;
	}

	public void setBlockImage(Image blockImage) {
		this.blockImage = blockImage;
	}

	public void setBallSpeed(int ballSpeed) {
		this.ballSpeed = ballSpeed;
	}

	public void setBallWidth(int ballWidth) {
		this.ballWidth = ballWidth;
	}

	public void setBallHeight(int ballHeight) {
		this.ballHeight = ballHeight;
	}

	public void setBallCenterX(int ballCenterX) {
		this.ballCenterX = ballCenterX;
	}

	public void setBallCenterY(int ballCenterY) {
		this.ballCenterY = ballCenterY;
	}

	public void setBallNextX(int ballNextX) {
		this.ballNextX = ballNextX;
	}

	public void setBallNextY(int ballNextY) {
		this.ballNextY = ballNextY;
	}

	public void setDrawingRangeWidth(int drawingRangeWidth) {
		this.drawingRangeWidth = drawingRangeWidth;
	}

	public void setDrawingRangeHeight(int drawingRangeHeight) {
		this.drawingRangeHeight = drawingRangeHeight;
	}

	public void setRacketX(int racketX) {
		this.racketX = racketX;
	}

	public void setRacketY(int racketY) {
		this.racketY = racketY;
	}

	public void setRacketWidth(int racketWidth) {
		this.racketWidth = racketWidth;
	}

	public void setRacketHeight(int racketHeight) {
		this.racketHeight = racketHeight;
	}

	public void setBlockExist(int index) {
		this.blockExist[index] = 0;
	}

	public void setBlockX(int[] blockX) {
		this.blockX = blockX;
	}

	public void setBlockY(int[] blockY) {
		this.blockY = blockY;
	}

	public void setBlockWidth(int blockWidth) {
		this.blockWidth = blockWidth;
	}

	public void setBlockHeight(int blockHeight) {
		this.blockHeight = blockHeight;
	}

	public void setBlockMargin(int blockMargin) {
		this.blockMargin = blockMargin;
	}

	public void setBlockRow(int blockRow) {
		this.blockRow = blockRow;
	}

	public void setBlockColumn(int blockColumn) {
		this.blockColumn = blockColumn;
	}

	public void setBlockQuantity(int count) {
		this.blockQuantity = blockQuantity + blockQuantity;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void blockset(){

		//ブロックがあるかどうかの配列変数
		blockExist = new int[blockRow * blockColumn];
		//ブロックのx座標
		blockX = new int[blockRow * blockColumn];
		//ブロックのy座標
		blockY = new int[blockRow * blockColumn];
		//ブロックの個数
		blockQuantity = blockRow * blockColumn;

		int count1;
		int count2;
		//ブロック番号変数
		int blockNumber = 0;
		//段毎のy座標変数
		int CoordinateY;

		for(count1 = 0; count1 < blockRow; count1++) {
			CoordinateY = count1 * (blockHeight + 3) + blockMargin;
			for (count2 = 0; count2 < blockColumn; count2++) {
				blockX[blockNumber] = count2 * (blockWidth + 30) + blockMargin;
				blockY[blockNumber] = CoordinateY;
				blockExist[blockNumber] = 1;
				blockNumber++;
			}
		}
	}
}

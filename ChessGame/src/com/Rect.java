package com;

public class Rect {
	Point leftTop;
	Point rightBottom;
	public Rect(int left, int top, int right, int bottom) {
		leftTop = new Point(left, top);
		rightBottom = new Point(right, bottom);
	}
	public Point getLeftTop() {
		return leftTop;
	}
	public void setLeftTop(Point leftTop) {
		this.leftTop = leftTop;
	}
	public Point getRightBottom() {
		return rightBottom;
	}
	public void setRightBottom(Point rightBottom) {
		this.rightBottom = rightBottom;
	}
}

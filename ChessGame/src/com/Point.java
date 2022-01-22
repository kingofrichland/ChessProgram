package com;

public class Point {
	int x = 0;
	int y = 0;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean equals(Object o){
		if (!(o instanceof Point)) return false;
		Point p = (Point)o;
		return (this.x==p.x) && (this.y==p.y);
	}
}

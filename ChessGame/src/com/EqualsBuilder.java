package com;
public class EqualsBuilder {
	
	boolean equalsValue = true;
	
	public EqualsBuilder(){}
	
	public EqualsBuilder append(int a, int b){
		equalsValue &= (a == b);
		return this;
	}
	
	public boolean isEquals(){
		return equalsValue;
	}
	
}

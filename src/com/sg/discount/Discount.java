package com.sg.discount;

public class Discount implements IDiscount{
	
	int iParentDiscount=0;
	int iDiscount=0;
	String strName;
	
	public Discount(int iParentDiscount, int iDiscount, String name) {
		super();
		this.iParentDiscount = iParentDiscount;
		this.iDiscount = iDiscount;
		strName = name;
	}

	@Override
	public int getParentDiscount() {
		return iParentDiscount;
	}

	@Override
	public int getDiscount() {
		return iDiscount;
	}

	@Override
	public String getName() {
		return strName;
	}

	@Override
	public String toString() {
		return "Discount [iParentDiscount=" + iParentDiscount + ", iDiscount="
				+ iDiscount + ", strName=" + strName + "]";
	}

	
	
}

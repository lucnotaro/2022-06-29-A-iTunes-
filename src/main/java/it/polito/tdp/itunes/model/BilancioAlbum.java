package it.polito.tdp.itunes.model;

import java.util.Objects;

public class BilancioAlbum implements Comparable<BilancioAlbum> {
	
	private Album a;
	private Integer b;
	public BilancioAlbum(Album a, Integer b) {
		super();
		this.a = a;
		this.b = b;
	}
	public Album getA() {
		return a;
	}
	public void setA(Album a) {
		this.a = a;
	}
	public Integer getB() {
		return b;
	}
	public void setB(Integer b) {
		this.b = b;
	}
	@Override
	public int hashCode() {
		return Objects.hash(a, b);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BilancioAlbum other = (BilancioAlbum) obj;
		return Objects.equals(a, other.a) && Objects.equals(b, other.b);
	}
	@Override
	public String toString() {
		return "BilancioAlbum [a=" + a + ", b=" + b + "]";
	}

	@Override
	public int compareTo(BilancioAlbum ba) {
		return ba.getB()-this.b;
	}
	
}

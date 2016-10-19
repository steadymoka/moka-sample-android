package com.moka.framework.util;


public enum ImageLocation {

	INNER(0), EXTERNAL(1), ETC(2);

	private final int index;

	ImageLocation(int index) {

		this.index = index;
	}

	public int getIndex() {

		return index;
	}

	public static ImageLocation get(int index) {

		for ( ImageLocation imageLocation : ImageLocation.values() )
			if ( imageLocation.getIndex() == index )
				return imageLocation;
		return EXTERNAL;
	}

}

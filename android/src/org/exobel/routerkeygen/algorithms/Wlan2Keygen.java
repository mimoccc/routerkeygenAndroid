/*
 * Copyright 2012 Rui Araújo, Luís Fonseca
 *
 * This file is part of Router Keygen.
 *
 * Router Keygen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Router Keygen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Router Keygen.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.exobel.routerkeygen.algorithms;

import java.util.List;

import org.exobel.routerkeygen.R;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <b>This only calculates the keys for some WLAN_xx</b>
 * <br> 
 * 
 * Many WLAN_XX don't use this algorithm.
 * Code by Kampanita
 */

public class Wlan2Keygen extends Keygen {

	final private String ssidIdentifier;
	public Wlan2Keygen(String ssid, String mac ) {
		super(ssid, mac);
		ssidIdentifier = ssid.substring(ssid.length()-2);
	}

	@Override
	public List<String> getKeys() {
		char[] key = new char[26];
		if (getMacAddress().length() != 12) {
			setErrorCode(R.string.msg_errpirelli);
			return null;
		}
		key[0] = getMacAddress().charAt(10);
		key[1] = getMacAddress().charAt(11);
		key[2] = getMacAddress().charAt(0);
		key[3] = getMacAddress().charAt(1);
		key[4] = getMacAddress().charAt(8);
		key[5] = getMacAddress().charAt(9);
		key[6] = getMacAddress().charAt(2);
		key[7] = getMacAddress().charAt(3);
		key[8] = getMacAddress().charAt(4);
		key[9] = getMacAddress().charAt(5);
		key[10] = getMacAddress().charAt(6);
		key[11] = getMacAddress().charAt(7);
		key[12] = getMacAddress().charAt(10);
		key[13] = getMacAddress().charAt(11);
		key[14] = getMacAddress().charAt(8);
		key[15] = getMacAddress().charAt(9);
		key[16] = getMacAddress().charAt(2);
		key[17] = getMacAddress().charAt(3);
		key[18] = getMacAddress().charAt(4);
		key[19] = getMacAddress().charAt(5);
		key[20] = getMacAddress().charAt(6);
		key[21] = getMacAddress().charAt(7);
		key[22] = getMacAddress().charAt(0);
		key[23] = getMacAddress().charAt(1);
		key[24] = getMacAddress().charAt(4);
		key[25] = getMacAddress().charAt(5);

		int max = 9;
		String begin = ssidIdentifier.substring(0,1);
		int primer_n = Integer.parseInt(begin, 16);  
		if (primer_n > max) {
		   String cadena = String.valueOf(key, 0, 2);  	
		   int value = Integer.parseInt(cadena,16);
		   value=value-1;
		   String cadena2 = Integer.toHexString(value);
		   if ( cadena2.length() < 2 )
			   cadena2 = "0" + cadena2;
		   key[0]=cadena2.charAt(0);
		   key[1]=cadena2.charAt(1);
		}
	
		addPassword(String.valueOf(key, 0, 26));
		return getResults();
	}
	

	private Wlan2Keygen(Parcel in) {
		super(in);
		ssidIdentifier = in.readString();
	}

	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(ssidIdentifier);
	}
	
    public static final Parcelable.Creator<Wlan2Keygen> CREATOR = new Parcelable.Creator<Wlan2Keygen>() {
        public Wlan2Keygen createFromParcel(Parcel in) {
            return new Wlan2Keygen(in);
        }

        public Wlan2Keygen[] newArray(int size) {
            return new Wlan2Keygen[size];
        }
    };

}

package com.ivandelic.prototype.warp.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ivandelic.prototype.warp.model.Universe;

/**
 * Serialization services for storing and restoring objects from external sources.
 * @author Ivan Delic
 */
public class SerialziationService {
	
	public static final void serialize(Universe universe, String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(universe);
			oos.flush();
			oos.close();
		} catch (IOException e) {
		}
	}
	
	public static final Universe deserialize(String filename) {
		Universe universe = null;
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			universe = (Universe) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
		}
		return universe; 
	}

}

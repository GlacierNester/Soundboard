package com.glacier.soundboard.err;

public enum ErrorTypes {
	NO_AUDIO_TO_SET("No Audio to Set the Photo to");
	
	ErrorTypes(String text)
	{
		this.text = text;
	}
	
	private final String text;

	@Override
	public String toString()
	{
		return text;
	}
}

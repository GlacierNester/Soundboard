package com.glacier.soundboard.err;

public enum ErrorTypes {
	NO_AUDIO_TO_SET("No Audio to Set the Photo to"), 
	ALREADY_EXISTS("You've already set up that one!"), 
	NO_AUDIOS_AVAILABLE("There aren't any audios to make a soundboard with!");
	
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

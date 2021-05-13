package com.firstbase.codetest.model.dto;

/**
 * Wraps random user information returned from https://randomuser.me/api/.
 */
public class RandomUser {

    /** User name information */
    private Name name;

    /** Picture information */
    private Picture picture;

    public Name getName() {
	return name;
    }

    public void setName(Name name) {
	this.name = name;
    }

    public Picture getPicture() {
	return picture;
    }

    public void setPicture(Picture picture) {
	this.picture = picture;
    }

    public static class Name {

	/** Title */
	private String title;

	/** First name */
	private String first;

	/** Last name */
	private String last;

	public String getTitle() {
	    return title;
	}

	public void setTitle(String title) {
	    this.title = title;
	}

	public String getFirst() {
	    return first;
	}

	public void setFirst(String first) {
	    this.first = first;
	}

	public String getLast() {
	    return last;
	}

	public void setLast(String last) {
	    this.last = last;
	}
    }

    public static class Picture {

	/** Large image */
	private String large;

	/** Medium image */
	private String medium;

	/** Thumbnail image */
	private String thumbnail;

	public String getLarge() {
	    return large;
	}

	public void setLarge(String large) {
	    this.large = large;
	}

	public String getMedium() {
	    return medium;
	}

	public void setMedium(String medium) {
	    this.medium = medium;
	}

	public String getThumbnail() {
	    return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
	    this.thumbnail = thumbnail;
	}
    }
}

/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2004-8 LearningPatterns Inc.
 */

package com.javatunes.util;

import java.util.Date;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MusicItem
    implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String title;
  private String artist;
  private Date releaseDate;
  private BigDecimal listPrice;
  private BigDecimal price;

  private static SimpleDateFormat c_dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public MusicItem() {

  }

  public MusicItem(Long id) {
    this.setId(id);
  }

  public MusicItem(Long id, String title, String artist,
      Date releaseDate, BigDecimal listPrice, BigDecimal price) {
    this.setId(id);
    this.setTitle(title);
    this.setArtist(artist);
    this.setReleaseDate(releaseDate);
    this.setListPrice(listPrice);
    this.setPrice(price);
  }

  public MusicItem(Long id, String title, String artist,
      String releaseDateString, BigDecimal listPrice, BigDecimal price) {
    try {
      this.setId(id);
      this.setTitle(title);
      this.setArtist(artist);
      this.setReleaseDate(c_dateFormatter.parse(releaseDateString));
      this.setListPrice(listPrice);
      this.setPrice(price);
    } catch (ParseException e) {
      e.printStackTrace();
      throw new IllegalArgumentException(
          "releaseDateString - bad format must be yyyy-MM-dd, was " + releaseDateString);
    }
  }

  private Long getId() {
    return id;
  }

  String getTitle() {
    return title;
  }

  String getArtist() {
    return artist;
  }

  Date getReleaseDate() {
    return releaseDate;
  }

  BigDecimal getListPrice() {
    return listPrice;
  }

  BigDecimal getPrice() {
    return price;
  }

  public String getReleaseDateString() {
    String result = null;
    Date releaseDate = this.getReleaseDate();

    if (releaseDate != null) {
      result = c_dateFormatter.format(releaseDate);
    }
    return result;
  }

  private void setId(Long idIn) {
    id = idIn;
  }

  private void setTitle(String titleIn) {
    title = titleIn;
  }

  private void setArtist(String artistIn) {
    artist = artistIn;
  }

  private void setReleaseDate(Date releaseDateIn) {
    releaseDate = releaseDateIn;
  }

  private void setListPrice(BigDecimal listPriceIn) {
    listPrice = listPriceIn;
  }

  private void setPrice(BigDecimal priceIn) {
    price = priceIn;
  }

  public boolean equals(Object compare) {
    boolean result = false;
    MusicItem other = null;

    if (compare instanceof MusicItem) {
      other = (MusicItem) compare;

      result = other.getId().equals(this.getId()) &&
          other.getTitle().equals(this.getTitle()) &&
          other.getArtist().equals(this.getArtist()) &&
          other.getReleaseDate().equals(this.getReleaseDate()) &&
          other.getListPrice().compareTo(this.getListPrice()) == 0 &&
          other.getPrice().compareTo(this.getPrice()) == 0;
    }
    return result;
  }

  public String toString() {
    return this.getClass().getName() +
        ": id=" + this.getId() +
        " title=" + this.getTitle() +
        " artist=" + this.getArtist() +
        " releaseDate=" + this.getReleaseDate() +
        " listPrice=" + this.getListPrice() +
        " price=" + this.getPrice();
  }
}
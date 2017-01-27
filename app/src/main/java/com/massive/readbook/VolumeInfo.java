package com.massive.readbook;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class VolumeInfo implements Serializable, Parcelable {
  private static final long serialVersionUID = 100L;

  public String title ;
  public String subtitle ;
  public List<String> authors ;
  public String publisher ;
  public String publishedDate ;
  public String description ;

  public int pageCount ;
  public String printType ;
  public List<String> categories ;
  public double averageRating ;
  public int ratingsCount ;
  public String maturityRating ;
  public boolean allowAnonLogging ;
  public String contentVersion ;
  public ImageLinks imageLinks ;
  public String language ;
  public String previewLink ;
  public String infoLink ;
  public String canonicalVolumeLink ;


  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.title);
    dest.writeString(this.subtitle);
    dest.writeStringList(this.authors);
    dest.writeString(this.publisher);
    dest.writeString(this.publishedDate);
    dest.writeString(this.description);

    dest.writeInt(this.pageCount);
    dest.writeString(this.printType);
    dest.writeStringList(this.categories);
    dest.writeDouble(this.averageRating);
    dest.writeInt(this.ratingsCount);
    dest.writeString(this.maturityRating);
    dest.writeByte(this.allowAnonLogging ? (byte) 1 : (byte) 0);
    dest.writeString(this.contentVersion);
    dest.writeSerializable(this.imageLinks);
    dest.writeString(this.language);
    dest.writeString(this.previewLink);
    dest.writeString(this.infoLink);
    dest.writeString(this.canonicalVolumeLink);
  }

  public VolumeInfo() {
  }

  protected VolumeInfo(Parcel in) {
    this.title = in.readString();
    this.subtitle = in.readString();
    this.authors = in.createStringArrayList();
    this.publisher = in.readString();
    this.publishedDate = in.readString();
    this.description = in.readString();
    this.pageCount = in.readInt();
    this.printType = in.readString();
    this.categories = in.createStringArrayList();
    this.averageRating = in.readDouble();
    this.ratingsCount = in.readInt();
    this.maturityRating = in.readString();
    this.allowAnonLogging = in.readByte() != 0;
    this.contentVersion = in.readString();
    this.imageLinks = (ImageLinks) in.readSerializable();
    this.language = in.readString();
    this.previewLink = in.readString();
    this.infoLink = in.readString();
    this.canonicalVolumeLink = in.readString();
  }

  public static final Parcelable.Creator<VolumeInfo> CREATOR = new Parcelable.Creator<VolumeInfo>() {
    @Override
    public VolumeInfo createFromParcel(Parcel source) {
      return new VolumeInfo(source);
    }

    @Override
    public VolumeInfo[] newArray(int size) {
      return new VolumeInfo[size];
    }
  };
}

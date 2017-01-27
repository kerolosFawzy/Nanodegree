package com.massive.readbook;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Minafaw on 27/01/2017.
 */

public class Item implements Serializable
{
    public String kind ;
    public String id ;
    public String etag ;
    public String selfLink ;
    public VolumeInfo volumeInfo ;
    public SaleInfo saleInfo ;
    public AccessInfo accessInfo ;
    public SearchInfo searchInfo ;
}

class IndustryIdentifier
{
    public String type ;
    public String identifier ;
}

 class ReadingModes
{
    public boolean text ;
    public boolean image ;
}

class ListPrice
{
    public double amount ;
    public String currencyCode ;
}

 class RetailPrice
{
    public double amount ;
    public String currencyCode ;
}

 class ListPrice2
{
    public double amountInMicros ;
    public String currencyCode ;
}

 class RetailPrice2
{
    public double amountInMicros ;
    public String currencyCode ;
}

 class Offer
{
    public int finskyOfferType ;
    public ListPrice2 listPrice ;
    public RetailPrice2 retailPrice ;
}

 class SaleInfo
{
    public String country ;
    public String saleability ;
    public boolean isEbook ;
    public ListPrice listPrice ;
    public RetailPrice retailPrice ;
    public String buyLink ;
    public List<Offer> offers ;
}

 class Epub
{
    public boolean isAvailable ;
    public String acsTokenLink ;
}

 class Pdf
{
    public boolean isAvailable ;
    public String acsTokenLink ;
}

 class AccessInfo
{
    public String country ;
    public String viewability ;
    public boolean embeddable ;
    public boolean publicDomain ;
    public String textToSpeechPermission ;
    public Epub epub ;
    public Pdf pdf ;
    public String webReaderLink ;
    public String accessViewStatus ;
    public boolean quoteSharingAllowed ;
}

 class SearchInfo
{
    public String textSnippet ;
}



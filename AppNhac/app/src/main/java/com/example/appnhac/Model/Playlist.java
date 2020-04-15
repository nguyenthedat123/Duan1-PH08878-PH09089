package com.example.appnhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

@SerializedName("IdPlayList")
@Expose
private String idPlayList;
@SerializedName("TenPL")
@Expose
private String tenPL;
@SerializedName("HinhPlayL")
@Expose
private String hinhPlayL;
@SerializedName("ICON")
@Expose
private String iCON;

public String getIdPlayList() {
return idPlayList;
}

public void setIdPlayList(String idPlayList) {
this.idPlayList = idPlayList;
}

public String getTenPL() { return tenPL; }

public void setTenPL(String tenPL) {
this.tenPL = tenPL;
}

public String getHinhPlayL() {
return hinhPlayL;
}

public void setHinhPlayL(String hinhPlayL) {
this.hinhPlayL = hinhPlayL;
}

public String getICON() {
return iCON;
}

public void setICON(String iCON) {
this.iCON = iCON;
}

}
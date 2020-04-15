package com.example.appnhac.Service;

import com.example.appnhac.Model.Album;
import com.example.appnhac.Model.Baihat;
import com.example.appnhac.Model.CDandTL;
import com.example.appnhac.Model.ChuDe;
import com.example.appnhac.Model.Playlist;
import com.example.appnhac.Model.Quangcao;
import com.example.appnhac.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("banner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlist.php")
    Call<List<Playlist>> getDataPlaylist();

    @GET("1chudevatheloaitrongngay.php")
    Call<CDandTL> getChuDevaTL();

    @GET("1albumhot.php")
    Call<List<Album>> GetAlbum();

    @GET("1tatcachude.php")
    Call<List<ChuDe>> GetAllChuDe();

    @FormUrlEncoded
    @POST("1theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaitheoChuDe(@Field( "idChuDe") String idChuDe);

    @GET("1tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();

    @GET("1baihatduocthich.php")
    Call<List<Baihat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("1danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("1danhsachcacplaylist.php")
    Call<List<Baihat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("1danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhsachcacPlaylist();

    @FormUrlEncoded
    @POST("1danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @FormUrlEncoded
    @POST("1danhsachcacplaylist.php")
    Call<String> UpdateLuotThich(@Field("LuotLike") String LuotLike,@Field("Idbaihat") String Idbaihat);
}

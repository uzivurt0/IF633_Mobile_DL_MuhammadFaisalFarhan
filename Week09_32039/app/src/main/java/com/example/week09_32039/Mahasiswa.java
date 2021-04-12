package com.example.week09_32039;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity (tableName = "tblMahasiswa")
public class Mahasiswa implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "nim")
    private String nim;

    @ColumnInfo (name = "nama")
    private String nama;

    @ColumnInfo (name = "email")
    private String email;

    @ColumnInfo (name = "nomorhp")
    private String nomorhp;

    public Mahasiswa(String nim, String nama, String email, String nomorhp){
        this.nim = nim;
        this.nama = nama;
        this.email = email;
        this.nomorhp = nomorhp;
    }

    @NonNull
    public String getNim() {
        return nim;
    }

    public void setNim(@NonNull String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomorhp() {
        return nomorhp;
    }

    public void setNomorhp(String nomorhp) {
        this.nomorhp = nomorhp;
    }
}

package model;

public class Loaisp {
    public int ID;
    public String Tenloaisp;
    public String Hinhanhloaisp;

    public Loaisp(int ID, String tenloaisp, String hinhanhloaisp) {
        this.ID = ID;
        Tenloaisp = tenloaisp;
        Hinhanhloaisp = hinhanhloaisp;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenloaisp() {
        return Tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        Tenloaisp = tenloaisp;
    }

    public String getHinhanhloaisp() {
        return Hinhanhloaisp;
    }

    public void setHinhanhloaisp(String hinhanhloaisp) {
        Hinhanhloaisp = hinhanhloaisp;
    }
}

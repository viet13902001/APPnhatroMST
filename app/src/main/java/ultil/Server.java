package ultil;

public class Server {
    //C1-102
//    public static String localhost = "192.168.43.250";
    //Staff
    public static String localhost = "mstappnhatro.000webhostapp.com";
//    public static String localhost = "192.168.1.102";

    public static String Duongdanloaisp = "http://"+ localhost +"/server/getloaisp.php";
    public static String Duongdansanphammoinhat ="http://"+ localhost+ "/server/getsanphammoinhat.php";
    public static String Duongdannhatro = "http://"+ localhost+ "/server/getsanpham.php?page=";
    public static String Duongdandonhang = "http://"+ localhost+ "/server/thongtinkhachhang.php";
    public static String Duongdanchitietdonhang = "http://"+ localhost+ "/server/chitietdonhang.php";
    public static String signup ="http://"+ localhost +"/server/signup.php";
    public static String signin ="http://"+ localhost +"/server/signin.php";
}
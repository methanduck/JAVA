package NetworkAPI;


public class Node {
    //Variables
    private String      Password;
    private String      IP_Addr;
    private String      HostName;
    private int         Temp;
    private int         Humidity;
    private boolean     Light;
    private int         Gas;

    public Node(String password, String IP_Addr, String hostName) {
        Password = password;
        this.IP_Addr = IP_Addr;
        HostName = hostName;
    }

    public Node(String IP_Addr) {
        this.IP_Addr = IP_Addr;
    }

    public String getPassword() {
        return Password;
    }

    public String getIP_Addr() {
        return IP_Addr;
    }

    public String getHostName() {
        return HostName;
    }

    public int getTemp() {
        return Temp;
    }

    public int getHumidity() {
        return Humidity;
    }

    public boolean isLight() {
        return Light;
    }

    public int getGas() {
        return Gas;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setIP_Addr(String IP_Addr) {
        this.IP_Addr = IP_Addr;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }
}

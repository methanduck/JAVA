package NetworkAPI;


public class Node {
    //Variables
    public boolean     initialized; //already configured node
    private String      Password;
    private String      IP_Addr;
    private String      HostName;
    public int         Temp;
    public int         Humidity;
    public boolean     Light;
    public int         Gas;

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

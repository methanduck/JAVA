package NetworkAPI;

public class Node {
    //Variables
    private String IP_Addr;
    private String HostName;
    private int     Temp;
    private int     Humidity;
    private boolean     Light;
    private int     Gas;

    public Node (String IP, String hostName)
    {
        this.IP_Addr= IP;
        this.HostName = hostName;
    }
    public Node (String ip)
    {
        this.IP_Addr = ip;
        this.HostName=null;
    }
    public Node()
    {
        this.IP_Addr = null;
        this.HostName = null;
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
}

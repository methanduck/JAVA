package NetworkAPI;

public class Node {
    private String IP_Addr;
    private String HostName;

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

    public String getHostName() {
        return HostName;
    }

    public String getIP_Addr() {
        return IP_Addr;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }
}

package NetworkAPI;

import OuterTest.RequestData;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

public interface Functions {
    public List<Byte> GetData();
    public void GetLocalAddr() throws UnknownHostException;
    public Object GetWindowAddr(List<Node> Active_Addr, boolean FLAG);
    public boolean WindowOptions(String Window,String Order);
}

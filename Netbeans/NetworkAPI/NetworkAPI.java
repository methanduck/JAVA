package NetworkAPI;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;


public class NetworkAPI extends NetworkAPI_var {

    //Initializing
    public NetworkAPI () {
        this.Flag_ERR = false;
        this.Active_IP_List = null;
        this.Socket_addr = null;
        this.Comm_data = null;
        this.Socket_client = null;

    }
        //Getting Data from Window which was selected by user

        public List<Byte> GetData() {
            return null;
        }



        //Getting Window address from LAN of Activated all interfaces on Host
        public Object GetWindowAddr(List<Node> Active_Addr, boolean FLAG) {
            List<Node> Window_IP_List = new ArrayList<Node>() ;
            if (!Flag_ERR) {
                try {
                    for (Node s : this.Active_IP_List) {
                        this.Socket_client = new Socket(s.getIP_Addr(), 6866);
                        this.In_stream = this.Socket_client.getInputStream();
                        this.Comm_data.add(this.In_stream.read());

                        if (this.Comm_data.toString().contains("HELLOWINDOW")) {
                            s.setHostName(Comm_data.toString().split(",")[1]);
                            Window_IP_List.add(s);
                        }
                    }
                } catch (Exception e) {
                    Flag_ERR = false;
                }
                return Window_IP_List;
            }
            else {
                Node Fail = new Node();
                Fail.setHostName("GetWindowFAIL");
                return Fail;
            }
        }

    public boolean WindowOptions(String window,String Order) {
        switch (Order)
        {
            case "OPEN" :
                break;
            case "CLOSE" :
                break;
            case "HUMI" :
                break;
            case "TEMP" :
                break;
                default:
                    break;

        }

        return false;
    }

        public void GetLocalAddr() throws UnknownHostException {
                try {
                    Enumeration netCard = NetworkInterface.getNetworkInterfaces();
                    while (netCard.hasMoreElements())
                    {
                        NetworkInterface nextcard = (NetworkInterface) netCard.nextElement();
                        Enumeration LocalAddr = nextcard.getInetAddresses();
                        while (LocalAddr.hasMoreElements())
                        {
                            InetAddress TmpAddr = (InetAddress) LocalAddr.nextElement();
                            if (TmpAddr.isReachable(nextcard,64,30))
                            {
                                Node node = new Node(TmpAddr.toString());
                                this.Active_IP_List.add(node);
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    this.Flag_ERR = true;
                }
        }

        public void COMM_SendMSG(String IPAddr, String Message) throws IOException {
            Socket Client = new Socket(IPAddr,6866);
            OutputStream NetOut = Client.getOutputStream();
            NetOut.write(Message.getBytes());
        }

        public String COMM_RecvMSG(String IPAddr) throws IOException {
            Socket Client = new Socket(IPAddr,6866);
            InputStream NetIn = Client.getInputStream();
            Vector<>
        }

        public String COMM_InteractiveMSG(String IPAddr, String Message)
        {

        }
}

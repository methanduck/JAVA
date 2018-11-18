package NetworkAPI;


import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;



public class NetworkAPI extends NetworkAPI_var {
    List<Node> Active_IP_List;

    //Initializing
    public NetworkAPI () {
        Active_IP_List = new ArrayList<Node>();
    }
        public boolean WindowOptions(String window,String Order) {
        switch (Order)
        {
            //TODO: 경우의수 확인
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

        public void GetLocalAddr() throws UnknownHostException,SocketException,IOException {
            this.Active_IP_List.clear();
            Socket Client;
            InetAddress tmpAddr;
            String IP_Pattern = "((\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";

            Enumeration netCard = NetworkInterface.getNetworkInterfaces();
            while (netCard.hasMoreElements()) {
                NetworkInterface nextcard = (NetworkInterface) netCard.nextElement();
                Enumeration LocalAddr = nextcard.getInetAddresses();
                while (LocalAddr.hasMoreElements()) {
                    tmpAddr = (InetAddress) LocalAddr.nextElement();
                    if (!(tmpAddr.getHostAddress().equals("127.0.0.1")) && (tmpAddr.getHostAddress().matches(IP_Pattern))) {
                        SubnetUtils calcAddr = new SubnetUtils(tmpAddr.getHostAddress()+"/"+Integer.toString(nextcard.getInterfaceAddresses().get(0).getNetworkPrefixLength()));
                        String[] allAddr = calcAddr.getInfo().getAllAddresses();
                        for (String targetIP : allAddr) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        (new Socket(targetIP, 6866)).close();
                                        System.out.println("Find :" + targetIP);
                                        //TODO : 찾아진 노드에 대한 정보를 만들어서 Active 리스트에 추가한다.
                                    } catch (Exception e) {
                                    }
                                }
                            }).start();
                        }

                    }
                }
            }
        }



        public void COMM_SendMSG(String IPAddr, String Message) throws IOException {
            Socket Client = new Socket(IPAddr,6866);
            OutputStream NetOut = Client.getOutputStream();
            NetOut.write(Message.getBytes());
            Client.close();
        }

        public String COMM_RecvMSG(String IPAddr) throws IOException {
            Socket Client = new Socket(IPAddr,6866);
            InputStream NetIn = Client.getInputStream();
            BufferedReader Buff_In = new BufferedReader(new InputStreamReader(NetIn));
            Client.close();
            String result = null;
            while ( (result =Buff_In.readLine()) != null){}
            return result;

        }

        public String COMM_InteractiveMSG(String IPAddr, String Message) throws IOException {
            Socket Client = new Socket(IPAddr,6866);
            OutputStream NetOut = Client.getOutputStream();
            InputStream NetIn = Client.getInputStream();
            BufferedReader Buff_In = new BufferedReader(new InputStreamReader(NetIn));
            NetOut.write(Message.getBytes());
            Client.close();

            String Result = null;
            while ((Result = Buff_In.readLine())!=null) {}

            return Result;
        }

        public boolean COMM_PortCheck(String IPAddr, int Port) {
        try {
            (new Socket(IPAddr,Port)).close();
        } catch (Exception e)
        {
            return false;
        }
        return true;
        }

}

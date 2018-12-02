package NetworkAPI;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class NetworkAPI {
    //Command Configuration
    public static final int SVRPORT = 6866;
    public static final String OPERATION_OPEN = "OPEN";
    public static final String OPERATION_CLOSE = "CLOSE";
    public static final String OPERATION_INFORMATION = "INFO";
    public static final String OPERATION_MODEAUTO   = "AUTO";
    public static final String COMM_OK = "NETOK";
    public static final String COMM_FAIL = "NETERR";
    //variables
    List<Node> Active_IP_List;

    //Initializer
    public NetworkAPI () {
        Active_IP_List = new ArrayList<Node>();
    }

    //node class should be initialized before provide this method
    public String StartNetworkAPI(Node window, String order) throws Exception {
        String COMM_validationResult;
        String CommandResult;
        String COMM_receivedData = null;
        Socket COMM_Window = new Socket(window.getIP_Addr(),SVRPORT);
        CommandResult = COMM_RecvMSG(window.getIP_Addr(), COMM_Window);
        switch (COMM_receivedData){
            case "CONFIG_REQUIRE" :
                COMM_SendMSG(window.getIP_Addr(), window.getPassword()+window.getHostName(), COMM_Window);
                break;

            case "IDENTIFICATION_REQUIRE" :
                COMM_SendMSG(window.getIP_Addr(), window.getPassword(), COMM_Window);
                COMM_validationResult = COMM_RecvMSG("0", COMM_Window);
                if (COMM_validationResult.equals("ERRVALIDATION")){
                    throw new Exception("ERRVALIDATION");
                }
                break;
        }
        try {
            CommandResult= WindowOperations(window, order,COMM_Window);
        }catch (Exception e){
            CommandResult = e.getMessage();
        }

        COMM_Window.close();
        return CommandResult;
    }

    // if not enough argumets provided, will throws "ARGUMENTSERR" exception
    // return "NETOK" , "NETERR", required DATA i.e.) case OPERATION_INFORMATION:
    public String WindowOperations(Node Window , String Order, Socket COMM_Window) throws Exception {
        String COMM_result = COMM_OK;
        String[] splitedOrder = null;
        splitedOrder = Order.split(":");

        switch (splitedOrder[0])
        {
            case OPERATION_OPEN :
                COMM_SendMSG(Window.getIP_Addr(),OPERATION_OPEN,COMM_Window);
                break;
            case OPERATION_CLOSE :
                COMM_SendMSG(Window.getIP_Addr(), OPERATION_CLOSE,COMM_Window);
                break;
            case OPERATION_INFORMATION:
                COMM_SendMSG(Window.getIP_Addr(), OPERATION_INFORMATION,COMM_Window);
                COMM_result=COMM_RecvMSG(Window.getIP_Addr(), COMM_Window);
                break;
            case OPERATION_MODEAUTO :
                if (splitedOrder.length < 2) {
                    throw new Exception("ARGUMENTSERR");
                }
                COMM_SendMSG(Window.getIP_Addr(), OPERATION_MODEAUTO,COMM_Window);
                break;

                default:
                    COMM_result = COMM_FAIL;
        }

        return COMM_result;
    }

    //retrieving All local smartwindow ip address with delimiter ";" ex) 192.168.0.1;192.168.0.4;
    public String FindWindow() throws UnknownHostException,SocketException,IOException {
        String STRLIST_IPADDR = "";
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
                                    (new Socket(targetIP, SVRPORT)).close();
                                    STRLIST_IPADDR.concat(targetIP+";");
                                } catch (Exception e) {
                                }
                            }
                        }).start();
                    }

                }
            }
        }
        return STRLIST_IPADDR;
    }

    public void COMM_SendMSG(String IPAddr, String Message,Socket Window) throws IOException {
        //one time connection
        if (Window == null) {
            Socket Client = new Socket(IPAddr,6866);
            OutputStream NetOut = Client.getOutputStream();
            NetOut.write(Message.getBytes());
            Client.close();
        }else {
            OutputStream NetOut = Window.getOutputStream();
            NetOut.write(Message.getBytes());
        }
    }

    public String COMM_RecvMSG(String IPAddr,Socket Window) throws IOException {
        String result = null;
        if (Window == null){
            Socket Client = new Socket(IPAddr,6866);
            InputStream NetIn = Client.getInputStream();
            BufferedReader Buff_In = new BufferedReader(new InputStreamReader(NetIn));
            Client.close();
            while ( (result=Buff_In.readLine()) != null){}
        } else{
            InputStream NetIn = Window.getInputStream();
            BufferedReader Buff_In = new BufferedReader(new InputStreamReader(NetIn));
            while ( (result=Buff_In.readLine()) != null){}
        }
        return result;
    }

    //**deprecated
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

    //**deprecated
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
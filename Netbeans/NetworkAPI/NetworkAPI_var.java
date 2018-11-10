package NetworkAPI;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class NetworkAPI_var {
    protected Socket Socket_client;
    protected InetSocketAddress Socket_addr;
    protected List<Node> Active_IP_List;
    protected boolean Flag_ERR;
    protected OutputStream Out_streamW;
    protected InputStream In_stream;
    protected Vector<Integer> Comm_data;
    protected BufferedReader Buff_reader;
    protected BufferedWriter Buff_writer;

}

#include <winsock2.h>
#include <cstdlib>
#include <windows.h>
#include <iostream>
 
using namespace std;
 
 
int main(){
	 
	    WSADATA WsaDat;
	        if (WSAStartup(MAKEWORD(2,2), &WsaDat) != 0){
			    cout<<"WSA FAILED\n";
			        cin.get();
				    return 0;
				        }
		 
		    SOCKET Socket;
		        Socket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
			    if (Socket == SOCKET_ERROR){
				        cout<<"Socket Failed to load\n";
					    cin.get();
					        return 0;
						    }
			     
			        SOCKADDR_IN server;
				 
				    server.sin_port=htons (7898);
				        server.sin_family = AF_INET;
					 
					    server.sin_addr.s_addr = INADDR_ANY;
					     
					        if (bind(Socket, (SOCKADDR *)(&server), sizeof (server)) == SOCKET_ERROR) 
							    { 
								        cout<<"BINDING FAILED\n";
									    cin.get();
									        return 0;
										    }
						    do {
							        listen(Socket, 6866);
								    } while (Socket != SOCKET_ERROR);
}

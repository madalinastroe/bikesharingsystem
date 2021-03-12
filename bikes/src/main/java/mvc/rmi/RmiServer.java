package mvc.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import mvc.model.UseerProfile;
import mvc.model.User;

@SpringBootApplication
public class RmiServer extends UnicastRemoteObject implements UseerProfile{

	private static final long serialVersionUID = 1L;

	protected RmiServer() throws RemoteException {
		super(0);
	}

	@Override
	public User findbyEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
           
        //Instantiate RmiServer
        RmiServer server = new RmiServer();

        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//localhost/RmiServer", server);
        System.out.println("PeerServer bound in registry");
    }
}

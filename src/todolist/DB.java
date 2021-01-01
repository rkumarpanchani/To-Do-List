/* It is important to create this file, as it will us to connect with the XAMPP server's. 
 * And we can communicate with the database and java.
 * Also, you need to save "mysql-connector-java-5.1.49" file somewhere in system. Never delete that file.
 * Then, maybe you get the mysql error, therefore, right click on todolist project, then Build Path, then configured build path, then select above saved file and apply.
 * ("jdbc:mysql://localhost:3306/todolist", "root", ""); Here 3306 is the port number and todolist is the project file name
*/

// Also install the XAMPP Control Panel, this is our server. And Apache and MySQL are important parameters to be installed. 

package todolist;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//When we use jdbc, then to connect mysql, we will need the mysql connector to connect with it.  
import com.mysql.jdbc.ResultSetMetaData;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class DB {
	Connection con=null;
	java.sql.PreparedStatement pst;
	public static Connection dbconnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //jdbc java database connectivity
			
			//"jdbc:mysql://localhost:3306/signupform" this is the url of our localhost database, which we just created using XAMPP and blank string "" is the password
			//conn is the object, and I returned it
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", ""); 
			return conn;
		}
		catch(Exception e2) {
			System.out.println(e2);
			return null;
		}
	}
}

package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.nio.channels.SelectableChannel;
import java.sql.*;
class Accountant{
	int id;
	String Aname;
	void login() {
		System.out.println("welcome");
	}
	
}

public class jdbc1 {//admin

	
		
		public static void main(String[] args) throws Exception{
			
			Scanner sc=new Scanner(System.in);

			
			
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myDatabase?useUnicode=true&characterEncoding=UTF-8",uname,pass);
			String url = "jdbc:mysql://localhost:3306/database1";
			System.out.println("Enter the admin details");
	        String user = sc.next();//root
	        String pass = sc.next();//rablu@123
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (Exception c) {
	            return;
	        }
	        try{
	        	Connection con = DriverManager.getConnection(url, user, pass);
	        Statement st=con.createStatement();
	        
	        while(true) {
	        
	        System.out.println("1:Addaccountant");
	        System.out.println("2.viewaccountant");
	        System.out.println("3.Deleteaccountant");
	        System.out.println("4.move to the next");
	        int ac=sc.nextInt();
	       

	        
	        if(ac==1) {	System.out.println("enter the accountant id(all numerics)");
			int a=sc.nextInt();
			sc.nextLine();
			System.out.println("enter the accountant name");
			
			String s=sc.nextLine();
	        String update="insert into student values("+a+",'"+s+"')";
	        int count =st.executeUpdate(update);
	        System.out.println("added sucessfully");}
	        
	        else if(ac==3) {
	        //take input must have to insert
	        	try {
	        	System.out.println("enter the id of accountant to be removed");
	        String del="delete from student where userid="+sc.nextInt();
	        int count2=st.executeUpdate(del);
	        System.out.println(count2+" "+"deleted");}
	        	catch(Exception e) {
	        		System.out.println(e);}
	        	}
	        
	        else if(ac==2) {
	        System.out.println("Accountants List");
	        String userdata="";
	        String query="select * from student";//start
	        ResultSet rs=st.executeQuery(query);
	        System.out.println("the accountants are");
	      
	        
	        while( rs.next()) {
	        userdata=rs.getInt(1)+":"+rs.getString(2);
	        System.out.println(userdata);}}
	        else if(ac==4) {
	        	System.out.println("move to the accountant login");
	        	break;}
	        }
	       
	       
	       
	       
	       
	       Accountant obj=new Accountant();
	       System.out.println("enter the accountant id no");
	       
	        obj.id=sc.nextInt();
	        
	        try {
	        	String search="select userid,username from student where userid="+obj.id;
	        	ResultSet rs=st.executeQuery(search);//modified as we close above within loop
	        	rs.next();
	        	String s1=rs.getString("username");
	        	obj.login();
	        	System.out.println(s1);
	        while(true) {
	        	System.out.println("1:add studentdetails");
	        	System.out.println("2:view studentdetails");
	        	System.out.println("3:edit studentdetails");
	        	System.out.println("4:delete studentdetails");
	        	System.out.println("5:exit");
	        	System.out.println("6:fee pending students");
	        	int t=sc.nextInt();
	        	if(t==1) {
	        		System.out.println("enter the student id(numericals)");
	        		int sid=sc.nextInt();
	        		System.out.println("enter the name of student");
	        		sc.nextLine();
	        		String sname=sc.nextLine();
	        		System.out.println("enter the total fee of the student:");
	        		int fee=sc.nextInt();
	        		System.out.println("enter the fee paid upto now");
	        		int pay=sc.nextInt();
	        		System.out.println("contact no of student:");sc.nextLine();
	        		String phn=sc.nextLine();
	        		System.out.println("mail id of student");sc.hasNextLine();
	        		String mail=sc.nextLine();
	        	String add="insert into studentdetails values("+sid+",'"+sname+"',"+fee+","+pay+",'"+phn+"','"+mail+"')";
	        	int count3=st.executeUpdate(add);
	        	System.out.println(count3+" enterd");}
	        	else if(t==2) {
	        	System.out.println("View Student details");
	        	String std="";
	        	String view="select * from studentdetails";
	        	rs=st.executeQuery(view);
	        	System.out.println("std_id:std_fee:paidfee:phn:mail");
	        	while(rs.next()) {
std=rs.getInt(1)+" :"+rs.getString(2)+":"+rs.getInt(3)+":"+rs.getInt(4)+":"+rs.getString(5)+":"+rs.getString(6);
	        		
	        		System.out.print(std);
	        		System.out.println(" :balance="+(rs.getInt(3)-rs.getInt(4)));
	        	}}
	        	
	        	else if(t==3) {
	        		
	        		try{String edit="update studentdetails set unpaid=? where std_id=?";
	        
	        		PreparedStatement psmt=con.prepareStatement(edit);
	        		System.out.println("id of the student ");
	        		int id=sc.nextInt();
	        		System.out.println("enter the paid amount upto now");
	        	int paid=sc.nextInt();
	        		psmt.setInt(1,paid);
	        		psmt.setInt(2,id);
	        		psmt.executeUpdate();
	        		System.out.println("updated");
	        		}
	        		catch(SQLException se) {
	        			System.out.println("throws error");
	        		}
	        		}
	        	else if(t==5) {
	        		break;
	        	}
//	        	newly modified part 
	        	
	        	else if(t==6) {
	        		System.out.println("View Student details of fee pending");
		        	String std="";
		        	String view="select * from studentdetails";
		        	rs=st.executeQuery(view);
		        	System.out.println("std_id:std_fee:paidfee:phn:mail");
		        	while(rs.next()) {
	std=rs.getInt(1)+" :"+rs.getString(2)+":"+rs.getInt(3)+":"+rs.getInt(4)+":"+rs.getString(5)+":"+rs.getString(6);
		        		if(rs.getInt(3)!=rs.getInt(4)) {
		        		System.out.print(std);
		        		System.out.println(" :balance="+(rs.getInt(3)-rs.getInt(4)));}
		        		}
	        		
	        	}
	        	
	//        	
	        	
	        	else if(t==4) {
	         	try {
		        	System.out.println("enter the id of accountant to be removed");
		        String del="delete from studentdetails where std_id="+sc.nextInt();
		        int count2=st.executeUpdate(del);
		        System.out.println(count2+" "+"deleted");}
		        	catch(Exception e) {
		        		System.out.println(e);}
		        	}
	        	}
	        	
	        	
	        
	        
	        }
	        catch(Exception f) {
	        	System.out.println("failed to get into Accountant login");
	        }
	        
	       
	       
	       
	      
	       
	        }
	        catch(Exception e) {
	        	System.out.println("error");
	        }
	       
	        

		}}

	



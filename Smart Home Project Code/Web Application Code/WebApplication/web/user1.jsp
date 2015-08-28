<%-- 
    Document   : tazim.jsp
    Created on : Mar 27, 2015, 11:33:21 PM
    Author     : ASUS
--%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            #myroom{
                height:80%;
                width: 80%;
            }
           
            #mainBox{
                height:400px;
                width: 90%;
                background-color: #6666FF;
                margin-left: 200px;
                margin-top: 60px;
                color: red;
                float:left;
            }
            #SignOut{
                float:right;
            }
            #child1{
                height: 99%;
                width: 32%;
                float:left;
                margin:1px;
                padding:1px;
                margin-left: 10px;
                text-align: center;
            }
            #child2{
                height: 99%;
                width: 32%;
                float:left;
                margin:1px;
                padding:1px;
                margin-left: 2px;
                text-align: center;
            }
            #child3{
                height: 99%;
                width: 32%;
                float:left;
                margin:1px;
                padding:1px;
                margin-left: 2px;
                text-align: center;
            }
            #light{
                height: 150px;
                border: 2px solid blue;
                text-align: center;
                padding: 50px;
                padding-top: 100px;
                margin-bottom: 15px;
                background-color:white;
                font: bold 30px Tahoma;
                
            }
            #button{
                height:25px;
                width:150px;
                border:0px;
                border-radius: 6px;
                font: bold 14px Tahoma;
                background: -webkit-linear-gradient( #FF3333 , white); /* For Safari 5.1 to 6.0 */
                background: -o-linear-gradient(#FF3333, white); /* For Opera 11.1 to 12.0 */
                background: -moz-linear-gradient(#FF3333, white); /* For Firefox 3.6 to 15 */
                background: linear-gradient(#FF3333, white); /* Standard syntax */
                box-shadow: 1px 1px 1px #FF3333;
               
            }
            #button:hover {
             height: 30px;
             width: 160px;
             background-color: #F4FA58;
             cursor:pointer;
             }
             
             #button1{
                height:25px;
                width:150px;
                border:0px;
                background-color: white;
                border-radius: 6px;
                font: bold 14px Tahoma;
                background: -webkit-linear-gradient( #47FF47 , white); /* For Safari 5.1 to 6.0 */
                background: -o-linear-gradient(#47FF47, white); /* For Opera 11.1 to 12.0 */
                background: -moz-linear-gradient(#47FF47, white); /* For Firefox 3.6 to 15 */
                background: linear-gradient(#47FF47, white); /* Standard syntax */
                box-shadow: 1px 1px 1px #47FF47;
            }
            #button1:hover {
             height: 30px;
             width: 160px;
             background-color: #F4FA58;
             cursor:pointer;
             }
             
             
             #log{
                height:35px;
                width:150px;
                background-color: white;
                border-radius: 6px;
                font: bold 14px Tahoma;
            }
            #log:hover {
             background-color: #FA5858;
             cursor:pointer;
             }
        </style>   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="2; url=http://localhost:8080/WebApplication/user1.jsp">
        <title>JSP Page</title>
    </head>
    <body id="myroom">
       <div id="SignOut">
          <form name="form4" action="LogOut" method="POST">
              <input id="log" type="submit" name="SignOut" value="Sign Out"/>
          </form> 
           
       </div>
        
        <div style="position:absolute; margin-left:400px; margin-top:30px;">
           <h2 style="color: #0040FF;">Welcome User1 to Smart Room Control Service </h2> 
         </div>  
        
        
        <div id="mainBox">
        
          <div id="child1">  
              
            
          <%
             // Set refresh, autoload time as 5 seconds
            // response.setIntHeader("Refresh", 5);
             
            String file1 = "C:/WebApplication/web/user1_Light1_Home_status.txt";
           // Path currentRelativePath = Paths.get("");
           // String filen = currentRelativePath.toAbsolutePath().toString()+"\\user1_Light1_Home_status.txt";
            
            BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            String L1;
            L1= reader1.readLine();
           // out.println(L1.toString());
          %>
          <% if(L1.equals("No Status!")) { %>
              <div id="light" style="background-color:white;" >   
                  <%out.println(L1.toString());%>
               </div>
          <% } else if(L1.equals("Light1 ON")){ %> 
               <div id="light" style="background-color:yellow;">
                   <%out.println(L1.toString());%>
               </div>
          <% } else if(L1.equals("Light1 OFF")){ %> 
               <div id="light" style="background-color:black;"> 
                   <%out.println(L1.toString());%>
               </div>
          <% }  %>
          <form name="form1" action="BtnClickRes" method="POST">
            <input id="button1" type="submit" name="Light1On" value="Light1 On"/><br/><br/>
            <input id="button" type="submit" name="Light1Off" value="Light1 Off"/>
          </form>
        
         </div> 
          
          <div id="child2">  
          <%
            String file2 = "C:/WebApplication/web/user1_Light2_Home_status.txt";
    
         // String file2 = currentRelativePath.toAbsolutePath().toString()+"/user1_Light2_Home_status.txt";  
          BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            String L2;
            L2= reader2.readLine();
           // out.println(L2.toString());
          %>
          <% if(L2.equals("No Status!")) { %>
              <div id="light" style="background-color:white;" >   
                  <%out.println(L2.toString());%>
               </div>
          <% } else if(L2.equals("Light2 ON")){ %> 
               <div id="light" style="background-color:yellow;">
                   <%out.println(L2.toString());%>
               </div>
          <% } else if(L2.equals("Light2 OFF")){ %> 
               <div id="light" style="background-color:black;"> 
                   <%out.println(L2.toString());%>
               </div>
          <% }  %>
          
        <form name="form2" action="BtnClickRes" method="POST">
            <input id="button1" type="submit" name="Light2On" value="Light2 On"/><br/><br/>
            <input id="button" type="submit" name="Light2Off" value="Light2 Off"/>
        </form>
         
         </div> 
        <div id="child3">   
          <%
            String file3 = "C:/WebApplication/web/user1_Light3_Home_status.txt";
          //String file3 = currentRelativePath.toAbsolutePath().toString()+"/user1_Light3_Home_status.txt";  
          BufferedReader reader3 = new BufferedReader(new FileReader(file3));
            String L3;
            L3= reader3.readLine();
           // out.println(L3.toString());
          %>
          <% if(L3.equals("No Status!")) { %>
              <div id="light" style="background-color:white;" >   
                  <%out.println(L3.toString());%>
               </div>
          <% } else if(L3.equals("Light3 ON")){ %> 
               <div id="light" style="background-color:yellow;">
                   <%out.println(L3.toString());%>
               </div>
          <% } else if(L3.equals("Light3 OFF")){ %> 
               <div id="light" style="background-color:black;"> 
                   <%out.println(L3.toString());%>
               </div>
          <% }  %>
        
        
         <form name="form3" action="BtnClickRes" method="POST">    
            <input id="button1" type="submit" name="Light3On" value="Light3 On"/><br/><br/>
            <input id="button" type="submit" name="Light3Off" value="Light3 Off"/>
        </form>
       </div>
        
         
        </div>
    </body>
</html>

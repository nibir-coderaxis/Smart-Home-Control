<%-- 
    Document   : index
    Created on : Mar 27, 2015, 11:55:49 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #myBody{
                height:400px;
                width:650px;
                margin-top: 80px;
                margin-left: 330px;
                text-align: center;
                border-radius: 15px;
                background: -webkit-linear-gradient( #6666FF , white); /* For Safari 5.1 to 6.0 */
                background: -o-linear-gradient(#6666FF, white); /* For Opera 11.1 to 12.0 */
                background: -moz-linear-gradient(#6666FF, white); /* For Firefox 3.6 to 15 */
                background: linear-gradient(#6666FF, white); /* Standard syntax */
                box-shadow: 5px 5px 5px #5882FA;
            }
            #myBox{
                margin-top: 90px;
                font: bold 14px Tahoma;
            }
            #log{
                height:35px;
                width:150px;
                margin-left: 80px;
                background-color: white;
                border-radius: 6px;
                font: bold 14px Tahoma;
            }
            #log:hover {
             background-color: #FA5858;
             cursor:pointer;
             }
        </style>
        
    </head>
    <body>
        <div id="myBody">
        <div style="">    
           <h1 style="color: #630063;">Smart Room Control Service</h1>
        </div>
        <div id="myBox">
         <form name="myForm" action="CheckLogin" method="POST">
            Username: <input type="text" name="username"/><br><br>
            Password: <input type="password" name="password"/><br><br>
            <input id="log" type="submit" value="Login"/>
         </form>
         </div> 
        </div>
    </body>
</html>

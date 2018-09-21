
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;


import javax.mail.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Initial
{
    public static void main(String[] args)
    {
        String[] goods = new String[2];
        //username and password not here
        try
        {
            goods = getInfo();
        }
        catch(Exception IOException)
        {
            System.out.println("Goofed");
        }
        String username = goods[0] + "@gmail.com";
        String password = goods[1];

        Properties props = new Properties();
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");

        Session session = Session.getInstance(props);/*,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                })*/;

        //session.setDebug(true);
        System.out.println(username);
        System.out.println(password);
        searchVideo("providence");
        /*try
        {
            Store myStore = session.getStore("pop3s");
            myStore.connect("pop.gmail.com",username,password);
            Folder inbox = myStore.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);
            //for(;;)
            //{
            System.out.println("------"+ inbox.getMessageCount()+"------");
            System.out.println("------"+ inbox.getUnreadMessageCount()+"------");
            System.out.println("------"+ inbox.getNewMessageCount()+"------");
                Message[] messages = inbox.getMessages();

                for (Message message : messages)
                {
                    Multipart part1 = (Multipart)message.getContent();
                    String content = part1.getBodyPart(0).getContent().toString();
                    System.out.println("0000000000000000000");
                    System.out.println(content);
                    System.out.println("0000000000000000000");
                    if(content.contains("!queue"))
                    {
                        System.out.println("Exclamation Test Successful");
                        System.out.println(content.replaceAll("!queue","").trim());
                        //searchVideo(content.replaceAll("!queue","").trim());
                    }
                    if(content.contains("%queue"))
                    {
                        System.out.println("Percent Test Successful");
                        System.out.println(content.replaceAll("%queue","").trim());
                    }
                    //message.setFlag(Flags.Flag.DELETED, true);

                }
            //}
            System.out.println("Done");
            inbox.close();
        }
        catch (MessagingException e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        catch (Exception IOException)
        {
            System.out.println(IOException.getMessage());
        }*/


    }

    public static String[] getInfo() throws IOException
    {
        Scanner s = null;
        String user = "";
        String pass = "";
        try
        {
            s = new Scanner(new BufferedReader(new FileReader("C:/Users/Owner/Desktop/credentials.txt")));
            user = s.nextLine();
            pass = s.nextLine();
        }
        finally
        {
            s.close();
        }
        return new String[]{user,pass};
    }
    public static void searchVideo(String searchTerm)
    {
        try
        {
            Document firstPage = Jsoup.connect("https://www.youtube.com/results?search_query=" + searchTerm).get();
            System.out.println(firstPage.title());
        }
        catch(IOException e)
        {
            System.out.println("Youtube failed");

        }
    }



}

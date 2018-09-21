
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;



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
        long Max = 5;
        try
        {
            //HttpRequest http = new HttpRequestInitializer()
            YouTube.Builder theBuilder = new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }});
            YouTube youtube = theBuilder.build();
            YouTube.Search.List search = youtube.search().list("id,snippet");
            search.setQ(searchTerm);
            search.setType("video");
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/default/url)");
            search.setMaxResults(Max);
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null)
            {
                System.out.println(searchResultList);
            }




        }
        catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }

    }



}

package gamemakerstudio_.misc.helpfulstuff;

import gamemakerstudio_.misc.util_;

import java.util.ArrayList;

public class MessingWithStringBuilder {
    public static void main(String[] args){
        // TODO: hey uh, newgrounds audio player, it's under test packages
        // FIXME: strings that has forward slash "/" bcz it causes bugs
        ArrayList <String> songId = new ArrayList<String>();
        ArrayList <String> title = new ArrayList<String>();
        ArrayList <String> artist = new ArrayList<String>();

        String woah = util_.loadFileAsString("C:\\Users\\ACER\\Downloads\\ng audio database\\" +
                "JKHP's Favorite Audio 1.html");
        String[] x = woah.split("/");

        for (int i = 0; i < x.length; i++){
//            System.out.println(i + ". " + x[i]);
            if (x[i].equals("listen")){
                String[] temp = x[i+1].split("\"");
                songId.add(temp[0]);
                title.add(temp[4]);
                String[] lev1 = x[i+5].split(">");
                String[] lev2 = lev1[3].split("<");
                artist.add(lev2[0]);
                /*String[] tempArtist = x[i+12].split("\\.");
                artist.add(tempArtist[0]);*/
            }
        }

        for (int i = 0; i < songId.size(); i++){
            System.out.println(
                    "title: " + title.get(i) +
                    "\nartist: " + artist.get(i) +
                    "\nsongId: " + songId.get(i) + "\n");
            // for database
//            System.out.println(title.get(i));
//            System.out.println(artist.get(i));
//            System.out.println(songId.get(i));
        }
    }
}
